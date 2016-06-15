package techkids.mad3.finalproject.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HoangDuong1607 on 5/14/2016.
 */
public class CalculationCustomView extends View {
    private final float TEXT_SIZE_DP = 48;
    private final float STROKE_WIDTH = 5;

    private float TEXT_SIZE_PX;
    private float VIEW_WIDTH;
    private float VIEW_HEIGHT;

    private Paint paint = new Paint();

    private int firstNumber = 0;
    private int secondNumber = 0;
    private String operator = "+";

    public CalculationCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float factor = getResources().getDisplayMetrics().density;
        TEXT_SIZE_PX = TEXT_SIZE_DP * factor;
        VIEW_WIDTH = getWidth();
        VIEW_HEIGHT = getHeight();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setTextSize(TEXT_SIZE_PX);
        paint.setTextAlign(Paint.Align.LEFT);
//        Draw operator
        canvas.drawText(this.operator, 0, 3 * TEXT_SIZE_PX / 2, paint);

        paint.setTextAlign(Paint.Align.RIGHT);

//        Draw first number
        canvas.drawText("" + this.firstNumber, VIEW_WIDTH, TEXT_SIZE_PX, paint);

//        Draw second number
        canvas.drawText("" + this.secondNumber, VIEW_WIDTH, 2 * TEXT_SIZE_PX, paint);

//        Draw line
        //canvas.drawLine(0, 5 * TEXT_SIZE_PX / 2, VIEW_WIDTH, 5 * TEXT_SIZE_PX / 2, paint);
        canvas.drawLine(0, 3* TEXT_SIZE_PX, VIEW_WIDTH, 2*TEXT_SIZE_PX, paint);

    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
