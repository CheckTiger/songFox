package cn.sxh.songfox.mvp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.sxh.songfox.R;
import cn.sxh.songfox.mvp.ui.fragment.CoolWidgetFragment;
import cn.sxh.songfox.mvp.ui.fragment.QuestionFragment;
import cn.sxh.songfox.mvp.ui.fragment.TechnologyFragment;
import cn.sxh.songfox.mvp.ui.fragment.UIViewFragment;
import cn.sxh.songfox.mvp.ui.fragment.UtilsFragment;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup rg;
    //*****************************成员区**************************************//
    private FragmentTransaction mTransaction;
    private TechnologyFragment  technologyFragment;
    private UIViewFragment      uiViewFragment;
    private CoolWidgetFragment  coolWidgetFragment;
    private UtilsFragment       utilsFragment;
    private QuestionFragment    questionFragment;
    //*****************************成员变量区**************************************//
    private int currentIndex = 0;

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
        hideTagFragment(mTransaction);
        switch (index) {
            case 0:
                if (technologyFragment == null) {
                    technologyFragment = new TechnologyFragment();
                    mTransaction.add(R.id.home_content, technologyFragment);
                } else {
                    mTransaction.show(technologyFragment);
                }
                break;
            case 1:
                if (uiViewFragment == null) {
                    uiViewFragment = new UIViewFragment();
                    mTransaction.add(R.id.home_content, uiViewFragment);
                } else {
                    mTransaction.show(uiViewFragment);
                }
                break;
            case 2:
                if (coolWidgetFragment == null) {
                    coolWidgetFragment = new CoolWidgetFragment();
                    mTransaction.add(R.id.home_content, coolWidgetFragment);
                } else {
                    mTransaction.show(coolWidgetFragment);
                }
                break;
            case 3:
                if (utilsFragment == null) {
                    utilsFragment = new UtilsFragment();
                    mTransaction.add(R.id.home_content, utilsFragment);
                } else {
                    mTransaction.show(utilsFragment);
                }
                break;
            case 4:
                if (questionFragment == null) {
                    questionFragment = new QuestionFragment();
                    mTransaction.add(R.id.home_content, questionFragment);
                } else {
                    mTransaction.show(questionFragment);
                }
                break;
                default:
                    break;
        }
        mTransaction.commit();
    }

    private void hideTagFragment(FragmentTransaction transaction) {
        if (technologyFragment != null) {
            transaction.hide(technologyFragment);
        }
        if (uiViewFragment != null) {
            transaction.hide(uiViewFragment);
        }
        if (coolWidgetFragment != null) {
            transaction.hide(coolWidgetFragment);
        }
        if (utilsFragment != null) {
            transaction.hide(utilsFragment);
        }
        if (questionFragment != null) {
            transaction.hide(questionFragment);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.technology_zone:
                currentIndex = 0;
                setTabSelectFragment(0);
                break;
            case R.id.technology_ui:
                currentIndex = 1;
                setTabSelectFragment(1);
                break;
            case R.id.technology_widget:
                currentIndex = 2;
                setTabSelectFragment(2);
                break;
            case R.id.technology_tools:
                currentIndex = 3;
                changedSelectedMenuUI();
                setTabSelectFragment(3);
                break;
            case R.id.technology_question:
                currentIndex = 4;
                setTabSelectFragment(4);
                break;
        }
    }

    private void changedSelectedMenuUI() {
        RadioButton radioButton = (RadioButton) rg.getChildAt(currentIndex);
        radioButton.setChecked(true);
    }

}
