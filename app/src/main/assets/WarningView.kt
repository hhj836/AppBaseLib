import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.BlurMaskFilter
import android.util.Log


/**
 * @author 侯慧杰
 * @date 2018/8/3
 * @Description:
 */

class WarningView :View {
    private lateinit var paint:Paint
    private lateinit var paint2:Paint
    var radius=10f
    var viewWidth=0
    var viewHeight=0
    var  rect= Rect()
    var dd=10
    constructor(ctx:Context):super(ctx){

        initPaint(ctx,null,0)



    }
    constructor(ctx:Context,attr: AttributeSet):super(ctx,attr){
        initPaint(ctx,attr,0)
    }
    constructor(ctx:Context,attr: AttributeSet?,style:Int):super(ctx,attr,style){
        initPaint(ctx,attr,style)
    }
    fun initPaint(ctx:Context,attr: AttributeSet?,style:Int){
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paint=Paint()
        paint.isAntiAlias=true
        paint.style=Paint.Style.FILL
        paint.strokeJoin=Paint.Join.ROUND
        paint.strokeWidth=5.0f
        paint.color= Color.RED

        paint2=Paint()
        paint2.isAntiAlias=true
        paint2.style=Paint.Style.FILL
        paint2.strokeJoin=Paint.Join.ROUND
        paint2.strokeWidth=5.0f
        paint2.xfermode= PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        paint2.color = Color.RED
        paint2.maskFilter = BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth= resolveSize(measuredWidth,widthMeasureSpec)
        viewHeight=resolveSize(measuredHeight,heightMeasureSpec)
     /*   rect.left= (radius).toInt()
        rect.top= (radius).toInt()
        rect.bottom= (viewHeight-radius).toInt()
        rect.right= (viewWidth-radius).toInt()*/

        rect.bottom= viewHeight
        rect.right= viewWidth
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.maskFilter = BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL)
        canvas?.drawRoundRect(RectF(rect),0f,0f,paint)



      //   paint.xfermode= PorterDuffXfermode(PorterDuff.Mode.CLEAR)
       /* canvas?.drawRoundRect(RectF(rect).apply {
            top=top+dd
            left=left+dd
            bottom=bottom-dd
            right=right-dd
        },20f,20f,paint)*/
   /*     paint.maskFilter=null
        canvas?.drawRoundRect(RectF(rect),10f,10f,paint)*/
        canvas?.drawRoundRect(RectF(rect).apply {
            top=top+dd
            left=left+dd
            bottom=bottom-dd
            right=right-dd
        },0f,0f,paint2)

        val paint3 = Paint(Paint.ANTI_ALIAS_FLAG)
        paint3.strokeWidth=5f
        paint3.style=Paint.Style.STROKE
        paint3.color = 0xffFF4081.toInt()
        canvas?.drawRoundRect(RectF(rect).apply {
            top=top+10
            left=left+10
            bottom=bottom-10
            right=right-10
        },0f,0f,paint3)
    }
    fun setColor(color:Int){
        paint.color=color
        invalidate()

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnim()
    }
    fun startAnim(){
       var anim= ValueAnimator.ofInt(10,20,10).setDuration(1500)
        anim.addUpdateListener { t-> dd = t.animatedValue as Int
        invalidate()}
        anim.repeatCount=ValueAnimator.INFINITE
        anim.start()

        var anim2= ValueAnimator.ofInt(50,255,50).setDuration(1500)
        anim2.addUpdateListener { t-> paint.alpha = t.animatedValue as Int
            paint2.alpha=t.animatedValue as Int
            invalidate()}
        anim2.repeatCount=ValueAnimator.INFINITE
        anim2.start()

    }
    fun stopAnim(){

    }
}