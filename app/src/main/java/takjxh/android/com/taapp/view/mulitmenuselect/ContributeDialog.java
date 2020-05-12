package takjxh.android.com.taapp.view.mulitmenuselect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.ContributeDialogAdapter;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.MyPagerAdapter;
import takjxh.android.com.taapp.view.mulitmenuselect.adapter.MyViewPager;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-11 8:54
 * @Description:
 **/
public class ContributeDialog  extends MultiDialog {
    private  int mWidth;
    private MyViewPager mViewPager;
    private LinearLayout mRootView;

    private List<View> views = new ArrayList<View>();
    public  ContributeDataManager mDictDataManager = ContributeDataManager.getInstance();
    private DictItemClickListener mDictItemClickListener;
    private ContributeDialogAdapter mListViewAdapter;

    LayoutInflater inflater;

    Contribute dictUnitN;
    List<Contribute> treeItemBeanList;
    public ContributeDialog(Context context,List<Contribute> treeItemBeanList) {
        super(context);
        this.treeItemBeanList=treeItemBeanList;
        mWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        inflater = LayoutInflater.from(context);
        mContentView = inflater.inflate(R.layout.layout_dialog,null);
        initViews();
        setTitle("选择投稿类型");
        dictdialog_title_tv1.setText("确定");
        dictdialog_title_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDictItemClickListener(dictUnitN);

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
        List<Contribute> list1=mDictDataManager.getTripleColumnData(treeItemBeanList, ContributeDataManager.START_ID);
        mListViewAdapter = new ContributeDialogAdapter(mContext, list1);
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
                final int num=views.indexOf(viewml);
                for(int i=0;i<num+1;i++){
                    mviews.add(views.get(i));
                }
                views.clear();
                views.addAll(mviews);


                Contribute dictUnit = (Contribute) parent.getItemAtPosition(position);
                dictUnitN = (Contribute) parent.getItemAtPosition(position);
                if (dictUnit.getCode().equals(ContributeDataManager.START_ID)) {//不限
                    ContributeDialogAdapter mListViewNAdapter = new ContributeDialogAdapter(mContext, new ArrayList<Contribute>());
                    if (mListViewNAdapter != null) {
                        mListViewNAdapter.setData(new ArrayList<Contribute>());
                        mListViewNAdapter.notifyDataSetChanged();
                    }
                    setDictItemClickListener(dictUnit);
                } else if(dictUnit.getSecondList()!=null && dictUnit.getSecondList().size()>0) {
                    View viewmN = inflater.inflate(R.layout.page1, null);
                    ListView mListViewN = (ListView) viewmN.findViewById(R.id.listview1);
                    List<Contribute> listN = mDictDataManager.getTripleColumnData(treeItemBeanList, dictUnit.getCode());
                    ContributeDialogAdapter  mListViewNAdapter = new ContributeDialogAdapter(mContext, listN);
                    mListViewNAdapter.setNormalBackgroundResource(R.color.white);
                    mListViewN.setAdapter(mListViewNAdapter);
                    setV(viewmN,mListViewN,mListViewNAdapter,listN);
                    views.add(viewmN);

                }else{
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


    private void setV(final View viewmN,final ListView mListViewN,final ContributeDialogAdapter  mListViewNAdapter,List<Contribute> listN){
        if(listN!=null && listN.size()>0){
            mListViewN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mListViewNAdapter != null) {
                        mListViewNAdapter.setSelectedItem(position);
                        mListViewNAdapter.setSelectedBackground(R.drawable.select_gray);
                    }

                    List<View> mviews = new ArrayList<View>();
                    final int num=views.indexOf(viewmN);
                    for(int i=0;i<num+1;i++){
                        mviews.add(views.get(i));
                    }
                    views.clear();
                    views.addAll(mviews);



                    View viewmN = inflater.inflate(R.layout.page1, null);
                    ListView mListViewN = (ListView) viewmN.findViewById(R.id.listview1);
                    dictUnitN = (Contribute) parent.getItemAtPosition(position);
                    Contribute dictUnit = (Contribute) parent.getItemAtPosition(position);
                    if(dictUnit.getSecondList()!=null &&  dictUnit.getSecondList().size()>0){
                        List<Contribute> listN = mDictDataManager.getTripleColumnData(treeItemBeanList, dictUnit.getCode());
                        ContributeDialogAdapter  mListViewNAdapter = new ContributeDialogAdapter(mContext, listN);
                        mListViewNAdapter.setHasDivider(false);
                        mListViewNAdapter.setNormalBackgroundResource(R.color.dictdialog_bg_gray);
                        mListViewN.setAdapter(mListViewNAdapter);
                        setV(viewmN,mListViewN,mListViewNAdapter,listN);

                        views.add(viewmN);
                    }else{
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
        }else{
            mListViewN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Contribute dictUnit = (Contribute) parent.getItemAtPosition(position);
                    dictUnitN = (Contribute) parent.getItemAtPosition(position);
                    setDictItemClickListener(dictUnit);

                }
            });
        }







    }

    private void setDictItemClickListener(Contribute dictUnit) {
        if (mDictItemClickListener != null) {
            mDictItemClickListener.onDictItemClick(dictUnit);
        }
        dismiss();
    }

    public final void setonItemClickListener(DictItemClickListener listener) {
        mDictItemClickListener = listener;
    }

    public interface DictItemClickListener {
        public void onDictItemClick(Contribute dictUnit);
    }
}