package cn.sxh.songfox;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup rg;
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        setTabSelectFragment(0);
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
    }

    private void setTabSelectFragment(int index) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case 0:
                Log.e("sxh","setTabSelectFragment: --------->>>>>"+index);
                break;
            case 1:
                Log.e("sxh","setTabSelectFragment: --------->>>>>"+index);
                break;
            case 2:
                Log.e("sxh","setTabSelectFragment: --------->>>>>"+index);
                break;
            case 3:
                Log.e("sxh","setTabSelectFragment: --------->>>>>"+index);
                break;
            case 4:
                Log.e("sxh","setTabSelectFragment: --------->>>>>"+index);
                break;
        }
        mTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.technology_zone:
                setTabSelectFragment(0);
                break;
            case R.id.technology_ui:
                setTabSelectFragment(1);
                break;
            case R.id.technology_widget:
                setTabSelectFragment(2);
                break;
            case R.id.technology_tools:
                setTabSelectFragment(3);
                break;
            case R.id.technology_question:
                setTabSelectFragment(4);
                break;
        }
    }
}
