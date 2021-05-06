package cn.sxh.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class NineView : View {

    // 二维数组初始化，int[3][3]
    private var mPoints: Array<Array<Point?>> = Array(3) { Array<Point?>(3, { null }) }
    // 是否初始化
    private var mIsInit = false
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    // 外圆的半径
    private var mDotRadius: Int = 0
    // 画笔
    private var mLinePaint: Paint? = null
    private var mPressedPaint: Paint? = null
    private var mErrorPaint: Paint? = null
    private var mNormalPaint: Paint? = null
    private var mArrowPaint: Paint? = null
    // 颜色
    private val mOuterPressedColor = 0xff8cbad8.toInt()
    private val mInnerPressedColor = 0xff0596f6.toInt()
    private val mOuterNormalColor = 0xffd9d9d9.toInt()
    private val mInnerNormalColor = 0xff929292.toInt()
    private val mOuterErrorColor = 0xff901032.toInt()
    private val mInnerErrorColor = 0xffea0945.toInt()


    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!mIsInit) {
            initPoint()
            initPaint()
            mIsInit = true
        }
    }

    /**
     * 初始化点
     */
    private fun initPoint() {
        //九个宫格，存到集合
        var width = this.width
        var height = this.height
        var squareWidth = width / 3
        var offsetY = (height - width) / 2
        mPoints[0][0]=Point(squareWidth/2,offsetY+squareWidth/2,0)
        mPoints[0][1]=Point(squareWidth*3/2,offsetY+squareWidth/2,1)
        mPoints[0][2]=Point(squareWidth*5/2,offsetY+squareWidth/2,2)
        mPoints[1][0]=Point(squareWidth/2,offsetY+squareWidth*3/2,3)
        mPoints[1][1]=Point(squareWidth*3/2,offsetY+squareWidth*3/2,4)
        mPoints[1][2]=Point(squareWidth*5/2,offsetY+squareWidth*3/2,5)
        mPoints[2][0]=Point(squareWidth/2,offsetY+squareWidth*5/2,6)
        mPoints[2][1]=Point(squareWidth*3/2,offsetY+squareWidth*5/2,7)
        mPoints[2][2]=Point(squareWidth*5/2,offsetY+squareWidth*5/2,8)
    }

    /**
     * 初始化画笔
     */
    private fun initPaint() {
        // 线的画笔
        mLinePaint = Paint()
        mLinePaint!!.color = mInnerPressedColor
        mLinePaint!!.style = Paint.Style.STROKE
        mLinePaint!!.isAntiAlias = true
        mLinePaint!!.strokeWidth = (mDotRadius / 9).toFloat()
        // 按下的画笔
        mPressedPaint = Paint()
        mPressedPaint!!.style = Paint.Style.STROKE
        mPressedPaint!!.isAntiAlias = true
        mPressedPaint!!.strokeWidth = (mDotRadius / 6).toFloat()
        // 错误的画笔
        mErrorPaint = Paint()
        mErrorPaint!!.style = Paint.Style.STROKE
        mErrorPaint!!.isAntiAlias = true
        mErrorPaint!!.strokeWidth = (mDotRadius / 6).toFloat()
        // 默认的画笔
        mNormalPaint = Paint()
        mNormalPaint!!.style = Paint.Style.STROKE
        mNormalPaint!!.isAntiAlias = true
        mNormalPaint!!.strokeWidth = (mDotRadius / 9).toFloat()
        // 箭头的画笔
        mArrowPaint = Paint()
        mArrowPaint!!.color = mInnerPressedColor
        mArrowPaint!!.style = Paint.Style.FILL
        mArrowPaint!!.isAntiAlias = true
    }

    class Point(var centerX: Int, var centerY: Int, var index: Int){
        private val STATUS_NORMAL = 1
        private val STATUS_PRESSED = 1
        private val STATUS_ERROR = 1
        private val status = STATUS_NORMAL
    }
}