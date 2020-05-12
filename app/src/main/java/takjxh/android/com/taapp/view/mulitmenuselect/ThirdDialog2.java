package takjxh.android.com.taapp.view.mulitmenuselect;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.ChildrenDialogAdapter;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.MyPagerAdapter;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.MyViewPager;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-06 15:52
 * @Description:
 **/
public class ThirdDialog2 extends MultiDialog {
    private int mWidth;
    private MyViewPager mViewPager;
    private LinearLayout mRootView;

    private List<View> views = new ArrayList<View>();
    public ChildrenDataManager mDictDataManager = ChildrenDataManager.getInstance();
    private DictItemClickListener mDictItemClickListener;
    private ChildrenDialogAdapter mListViewAdapter;

    LayoutInflater inflater;

    Children dictUnitN;
    List<Children> treeItemBeanList;

    public ThirdDialog2(Context context, List<Children> treeItemBeanList) {
        super(context);
        this.treeItemBeanList = treeItemBeanList;
        for (Children bean : treeItemBeanList) {
            if (bean != null && !TextUtils.isEmpty(bean.getParentId())) {
                ChildrenDataManager.START_ID = bean.getParentId();
                break;
            }
        }
        mWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        inflater = LayoutInflater.from(context);
        mContentView = inflater.inflate(R.layout.layout_dialog, null);
        initViews();
        setTitle("选择所属行业");
        dictdialog_title_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setDictItemClickListener(dictUnitN);
                dismiss();
            }
        });
    }

    private void initViews() {
        mRootView = (LinearLayout) findViewById(R.id.rootview);
        mViewPager = (MyViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);


        views.clear();

        final View viewml = inflater.inflate(R.layout.page1, null);
        ListView mListView = (ListView) viewml.findViewById(R.id.listview1);
        List<Children> list1 = mDictDataManager.getTripleColumnData(treeItemBeanList, ChildrenDataManager.START_ID);
        mListViewAdapter = new ChildrenDialogAdapter(mContext, list1);
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
                    ChildrenDialogAdapter mListViewNAdapter = new ChildrenDialogAdapter(mContext, new ArrayList<Children>());
                    if (mListViewNAdapter != null) {
                        mListViewNAdapter.setData(new ArrayList<Children>());
                        mListViewNAdapter.notifyDataSetChanged();
                    }
                    setDictItemClickListener(dictUnit);
                } else if (dictUnit.getChildren() != null && dictUnit.getChildren().size() > 0) {
                    View viewmN = inflater.inflate(R.layout.page1, null);
                    ListView mListViewN = (ListView) viewmN.findViewById(R.id.listview1);
                    List<Children> listN = mDictDataManager.getTripleColumnData(treeItemBeanList, dictUnit.getId());
                    ChildrenDialogAdapter mListViewNAdapter = new ChildrenDialogAdapter(mContext, listN);
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
                        ChildrenDialogAdapter mListViewNAdapter = new ChildrenDialogAdapter(mContext, listN);
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
        if (mDictItemClickListener != null) {
            mDictItemClickListener.onDictItemClick(dictUnit);
        }
        dismiss();
    }

    public final void setonItemClickListener(DictItemClickListener listener) {
        mDictItemClickListener = listener;
    }

    public interface DictItemClickListener {
        public void onDictItemClick(Children dictUnit);
    }
}