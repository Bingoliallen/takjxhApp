package takjxh.android.com.taapp.view.mulitmenuselect;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.view.NormalTitleBar;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.ChildrenDialogAdapter;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.MyPagerAdapter;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.MyViewPager;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-15 13:40
 * @Description:
 **/
public class MultiDialogActivity extends BaseActivity {

    public static void startAction(Activity activity,String title, ArrayList<Children> treeItemBeanList, int requestCode) {
        Intent intent = new Intent(activity, MultiDialogActivity.class);
        intent.putExtra("title", title);
        intent.putParcelableArrayListExtra("treeItemBeanList",treeItemBeanList);
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.edSearch)
    AutoCompleteTextView edSearch;

    String title;

    @BindView(R.id.rootview)
    LinearLayout mRootView;

    @BindView(R.id.viewpager)
    MyViewPager mViewPager;

    LayoutInflater inflater;
    private List<View> views = new ArrayList<View>();

    public ChildrenDataManager mDictDataManager = ChildrenDataManager.getInstance();

    private ChildrenDialogAdapter mListViewAdapter;

    private Children dictUnitN;
    private ArrayList<Children> treeItemBeanList;
    private List<Children> list10=new ArrayList<>();

    private Children dictUnitN10;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_multidialog;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();

        BarUtil.hideActionBar(this);
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();
        title = getIntent().getStringExtra("title");
        treeItemBeanList = getIntent().getParcelableArrayListExtra("treeItemBeanList");


        for (Children bean : treeItemBeanList) {
            if (bean != null && !TextUtils.isEmpty(bean.getParentId())) {
                ChildrenDataManager.START_ID = bean.getParentId();
                break;
            }
        }
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText(title);
        ntb.setTvLeftVisiable(true);
        ntb.getTvTitle().requestFocus();
        ntb.setRightTitle("确定");
        ntb.setRightTitleVisibility(false);
        ntb.setOnRightTextListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edSearch.getText().toString().trim())){
                    ToastUtil.showToast(MultiDialogActivity.this,"请"+title);
                }else{
                    if(dictUnitN10==null){
                        ToastUtil.showToast(MultiDialogActivity.this,"您输入的所属行业名称不存在");
                    }else{
                        setDictItemClickListener(dictUnitN10);
                    }
                }
            }
        });

        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        inflater = LayoutInflater.from(MultiDialogActivity.this);
        mViewPager.setOffscreenPageLimit(2);


        views.clear();
        final View viewml = inflater.inflate(R.layout.page1, null);
        ListView mListView = (ListView) viewml.findViewById(R.id.listview1);
        List<Children> list1 = mDictDataManager.getTripleColumnData(treeItemBeanList, ChildrenDataManager.START_ID);
        mListViewAdapter = new ChildrenDialogAdapter(MultiDialogActivity.this, list1);
        mListViewAdapter.setSelectedBackground(R.drawable.select_white);
        mListViewAdapter.setHasDivider(false);
        mListViewAdapter.setNormalBackgroundResource(R.color.dictdialog_bg_gray);
        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListViewAdapter != null) {
                    mListViewAdapter.setSelectedItem(position);

                }

                List<View> mviews = new ArrayList<View>();
                final int num = views.indexOf(viewml);
                for (int i = 0; i < num + 1; i++) {
                    mviews.add(views.get(i));
                }
                views.clear();
                views.addAll(mviews);


                Children dictUnit = (Children) parent.getItemAtPosition(position);
                dictUnitN = (Children) parent.getItemAtPosition(position);
                if (dictUnit.getId().equals(ChildrenDataManager.START_ID)) {//不限
                    ChildrenDialogAdapter mListViewNAdapter = new ChildrenDialogAdapter(MultiDialogActivity.this, new ArrayList<Children>());
                    if (mListViewNAdapter != null) {
                        mListViewNAdapter.setData(new ArrayList<Children>());
                        mListViewNAdapter.notifyDataSetChanged();
                    }
                    setDictItemClickListener(dictUnit);
                } else if (dictUnit.getChildren() != null && dictUnit.getChildren().size() > 0) {
                    View viewmN = inflater.inflate(R.layout.page1, null);
                    ListView mListViewN = (ListView) viewmN.findViewById(R.id.listview1);
                    List<Children> listN = mDictDataManager.getTripleColumnData(treeItemBeanList, dictUnit.getId());
                    ChildrenDialogAdapter mListViewNAdapter = new ChildrenDialogAdapter(MultiDialogActivity.this, listN);
                    mListViewNAdapter.setNormalBackgroundResource(R.color.white);
                    mListViewN.setAdapter(mListViewNAdapter);
                    setV(viewmN, mListViewN, mListViewNAdapter, listN);
                    views.add(viewmN);

                } else {
                    setDictItemClickListener(dictUnit);
                }

                mViewPager.getAdapter().notifyDataSetChanged();
                if (mViewPager.getCurrentItem() != num) {
                    mViewPager.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(num);
                        }
                    }, 300);
                }
            }
        });


        views.add(viewml);
        mViewPager.setAdapter(new MyPagerAdapter(views));
        mRootView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        list10.clear();
        list10=ChildrenUtil.getSelList(treeItemBeanList);
        String[] dictionary = new String[list10.size()];
        for(int i=0;i<list10.size();i++){
            dictionary[i]=list10.get(i).getName();
        }

        //利用适配器
        ArrayAdapter<String> adapter_actv = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,dictionary);
        edSearch.setAdapter(adapter_actv);




    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        TextWatcher textWatcher2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString().trim())){
                    ntb.setRightTitleVisibility(true);

                }else{
                    ntb.setRightTitleVisibility(false);
                }
                dictUnitN10=null;
            }
        };
        edSearch.addTextChangedListener(textWatcher2);
        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideSoftKeyboard(v);

                    //do...
                    if(TextUtils.isEmpty(edSearch.getText().toString().trim())){
                        ToastUtil.showToast(MultiDialogActivity.this,"请"+title);
                    }else{
                        if(dictUnitN10==null){
                            ToastUtil.showToast(MultiDialogActivity.this,"您输入的所属行业名称不存在");
                        }else{
                            setDictItemClickListener(dictUnitN10);
                        }
                    }

                    return true;
                }
                return false;
            }
        });

        edSearch.setThreshold(1);
        edSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){//获取焦点时
                    edSearch.showDropDown();
                }
            }
        });
        edSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // sshyID = list10.get(position).getId();
                dictUnitN10 = list10.get(position);
            }
        });

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();

    }

    private void setV(final View viewmN, final ListView mListViewN, final ChildrenDialogAdapter mListViewNAdapter, List<Children> listN) {
        if (listN != null && listN.size() > 0) {
            mListViewN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mListViewNAdapter != null) {
                        mListViewNAdapter.setSelectedItem(position);
                        mListViewNAdapter.setSelectedBackground(R.drawable.select_gray);
                    }

                    List<View> mviews = new ArrayList<View>();
                    final int num = views.indexOf(viewmN);
                    for (int i = 0; i < num + 1; i++) {
                        mviews.add(views.get(i));
                    }
                    views.clear();
                    views.addAll(mviews);


                    View viewmN = inflater.inflate(R.layout.page1, null);
                    ListView mListViewN = (ListView) viewmN.findViewById(R.id.listview1);
                    dictUnitN = (Children) parent.getItemAtPosition(position);
                    Children dictUnit = (Children) parent.getItemAtPosition(position);
                    if (dictUnit.getChildren() != null && dictUnit.getChildren().size() > 0) {
                        List<Children> listN = mDictDataManager.getTripleColumnData(treeItemBeanList, dictUnit.getId());
                        ChildrenDialogAdapter mListViewNAdapter = new ChildrenDialogAdapter(MultiDialogActivity.this, listN);
                        mListViewNAdapter.setHasDivider(false);
                        mListViewNAdapter.setNormalBackgroundResource(R.color.dictdialog_bg_gray);
                        mListViewN.setAdapter(mListViewNAdapter);
                        setV(viewmN, mListViewN, mListViewNAdapter, listN);

                        views.add(viewmN);
                    } else {
                        setDictItemClickListener(dictUnit);
                    }

                    mViewPager.getAdapter().notifyDataSetChanged();

                    if (mViewPager.getCurrentItem() != num) {
                        mViewPager.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mViewPager.setCurrentItem(num);
                            }
                        }, 300);
                    }
                }
            });
        } else {
            mListViewN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Children dictUnit = (Children) parent.getItemAtPosition(position);
                    dictUnitN = (Children) parent.getItemAtPosition(position);
                    setDictItemClickListener(dictUnit);

                }
            });
        }


    }


    private void setDictItemClickListener(Children dictUnit) {
        Intent result = new Intent();
        result.putExtra("dictUnit", dictUnit);
        setResult(RESULT_OK, result);
        finish();
    }

    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public static void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


}
