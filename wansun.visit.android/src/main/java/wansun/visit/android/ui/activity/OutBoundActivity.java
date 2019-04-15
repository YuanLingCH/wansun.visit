package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import soundrecorderutils.RecordActivity;
import wansun.visit.android.R;
import wansun.visit.android.adapter.AttachmentListAdapter;
import wansun.visit.android.adapter.visitAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseDetailBean;
import wansun.visit.android.bean.fileInfoCustomerBean;
import wansun.visit.android.db.fileInfo;
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.greendao.gen.fileInfoDao;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;
import wansun.visit.android.utils.unixTime;
import wansun.visit.android.widget.ListViewForScrollView;

/**
 * 案件详情界面
 * Created by User on 2019/1/22.
 */

public class OutBoundActivity extends BaseActivity {
    private fileInfoDao dao;
    ImageView iv_visit_back;
    ListView lv_visit;
    visitAdapter adapter;
    List data;
    String caseCode;
    String visitGuid;
    TextView tv_debtor_name,tv_debtor_money,tv_debtor_adress,tv_base_collecta_mount,tv_base_last_arrears,tv_visit_area,tv_visit_goal;
    TextView tv_visit_remark,tv_customer_name,tv_gender,tv_clerk,tv_visitors,tv_collect_status,tv_deadline,tv_entrust_date,tv_visit_opertor;
    TextView tvAppDate,tvApplicant,tv_card_address,tvVisitStatus,tvIdno,tvLastProcessDate,tv_visit_caseid,tvBatchCode;
    RelativeLayout rl_phone,rl_Address,rl_urge_recode,rl_visit_record,rl_visit_cord_detail,rl_visit_file_query;
    TextView tv_fCamera,tv_fRecord,tv_fFile,tv_fVideo,tv_file_upload;
    LinearLayout ll;  //地址整个布局
    ListViewForScrollView lvAttachment;  //底部附件lv
    AttachmentListAdapter bottomAdapter;
   List <fileInfoCustomerBean>bottomData;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_out_round;
    }

    @Override
    protected void initView() {
        getIntentData();
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
     //   lv_visit= (ListView) findViewById(R.id.lv_visit);
        tv_debtor_name= (TextView) findViewById(R.id.tv_debtor_name);
        tv_debtor_money= (TextView) findViewById(R.id.tv_debtor_money);
        tv_debtor_adress= (TextView) findViewById(R.id.tv_debtor_adress);
        tv_base_collecta_mount= (TextView) findViewById(R.id.tv_base_collecta_mount);
        tv_base_last_arrears= (TextView) findViewById(R.id.tv_base_last_arrears);
        tv_visit_area= (TextView) findViewById(R.id.tv_visit_area);
        tv_visit_goal= (TextView) findViewById(R.id.tv_visit_goal);
        tv_visit_remark= (TextView) findViewById(R.id.tv_visit_remark);
        tv_customer_name= (TextView) findViewById(R.id.tv_customer_name);
        tv_gender= (TextView) findViewById(R.id.tv_gender);
        tv_clerk= (TextView) findViewById(R.id.tv_clerk);
        tv_visitors= (TextView) findViewById(R.id.tv_visitors);
        tv_collect_status= (TextView) findViewById(R.id.tv_collect_status);
        tv_deadline= (TextView) findViewById(R.id.tv_deadline);
        tv_entrust_date= (TextView) findViewById(R.id.tv_entrust_date);
        rl_phone= (RelativeLayout) findViewById(R.id.rl_phone);
        rl_Address= (RelativeLayout) findViewById(R.id.rl_Address);
        rl_urge_recode= (RelativeLayout) findViewById(R.id.rl_urge_recode);
        rl_visit_record= (RelativeLayout) findViewById(R.id.rl_visit_record);
        rl_visit_cord_detail= (RelativeLayout) findViewById(R.id.rl_visit_cord_detail);
        tv_visit_opertor= (TextView) findViewById(R.id.tv_visit_opertor);
        tv_fCamera= (TextView) findViewById(R.id.tv_fCamera);
        tv_fRecord= (TextView) findViewById(R.id.tv_fRecord);
        tv_fFile= (TextView) findViewById(R.id.tv_fFile);
        tv_fVideo= (TextView) findViewById(R.id.tv_fVideo);
        tvAppDate= (TextView) findViewById(R.id.tvAppDate);
        tvApplicant= (TextView) findViewById(R.id.tvApplicant);
        tv_card_address= (TextView) findViewById(R.id.tv_card_address);
        tvVisitStatus= (TextView) findViewById(R.id.tvVisitStatus);
        tvIdno= (TextView) findViewById(R.id.tvIdno);
        tvLastProcessDate= (TextView) findViewById(R.id.tvLastProcessDate);
        tv_visit_caseid= (TextView) findViewById(R.id.tv_visit_caseid);
        tvBatchCode= (TextView) findViewById(R.id.tvBatchCode);
        ll= (LinearLayout) findViewById(R.id.ll);
        tv_file_upload= (TextView) findViewById(R.id.tv_file_upload);
        rl_visit_file_query= (RelativeLayout) findViewById(R.id.rl_visit_file_query);


    }


    private void getIntentData() {
         caseCode = getIntent().getStringExtra("caseCode");
         visitGuid = getIntent().getStringExtra("visitGuid");
        logUtils.d("案件编号"+caseCode+"visitGuid"+visitGuid);
        SharedUtils.putString("caseCode",caseCode);
        SharedUtils.putString("visitGuid",visitGuid );
    }

    @Override
    protected void initEvent() {
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
            }
        });
        rl_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUtils.d("点击案件电话");
                Intent intent=new Intent(OutBoundActivity.this,CasePhoneActivity.class);
                intent.putExtra("caseCode",caseCode);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果
            }
        });
        rl_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //急催记录
        rl_urge_recode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,UrgeRecordActivity .class);
                intent.putExtra("caseCode",caseCode);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果
            }
        });
        // 外访记录
        rl_visit_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,CaseVisitRecordAcitvity .class);
            //    intent.putExtra("visitRecord", (Serializable) visitRecord);
                intent.putExtra("caseCode",caseCode);
                intent.putExtra("visitGuid",visitGuid);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果
            }
        });
        // 卡信息
        rl_visit_cord_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,CaseCardMessageActivity .class);
                intent.putExtra("caseCode",caseCode);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果


            }
        });
        //操作界面
        tv_visit_opertor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUtils.d("onclick"+caseCode+":"+visitGuid);
                Intent intent=new Intent(OutBoundActivity.this,OperationActivity.class);
                startActivity(intent);
                intent.putExtra("caseCode",caseCode);
                intent.putExtra("visitGuid",visitGuid);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
        //拍照
        tv_fCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(OutBoundActivity.this,TakePhotosActivity.class);
                startActivity(intent);


            }
        });
        // 录音
        tv_fRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this, RecordActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
        //选择图片
        tv_fFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this, PictureSelectActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
        //录制视频界面
        tv_fVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,VideoRecorderActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
        //跳到定位界面去
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把地址传过去定位
  /*              String trim = tv_debtor_adress.getText().toString().trim();
                String[] split = trim.split("欠款人地址：");
                String address = split[1];
                logUtils.d("地址显示："+address);
                Intent intent=new Intent(OutBoundActivity.this,MainActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);*/

            }
        });
        /**
         * 文件上传  图片 ，文件 ，视频，录音
         */
        tv_file_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,FileUploadActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
        /**
         * 文件查询
         */
        rl_visit_file_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,FileQueryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });

        /**
         * 底部附件lv
         *
         */


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao= waifangApplication.getInstence().getSession().getFileInfoDao();

    }

    @Override
    protected void onStart() {
        super.onStart();
      //  loadBottomLvData();
    }

    /**
     * 加载底部数据
     */
    private void loadBottomLvData() {
        bottomData=new ArrayList();

        List<fileInfo> fileInfos = dao.loadAll();
        if (fileInfos.size()>0){
        Iterator<fileInfo> iterator = fileInfos.iterator();
        bottomData.clear();

        while (iterator.hasNext()){    //  遍历数据
            fileInfoCustomerBean bean=new fileInfoCustomerBean();
            fileInfo next = iterator.next();
            logUtils.d("测试数据"+next.path);
            bean.setId(next.getId());
            bean.setPath(next.getPath());
            bean.setType(next.getType());
            bottomData.add(bean);
        }

        bottomAdapter=new AttachmentListAdapter(OutBoundActivity.this,bottomData);
            lvAttachment.setVisibility(View.VISIBLE);
        lvAttachment.setAdapter(bottomAdapter);


   }

    }


    /**
     * 数据加载
     */
    @Override
    protected void initData() {
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        //TODO注意换回数据 caseData
        final RequestBody requestBody = requestBodyUtils.visitCaseDetailsFromeService(caseCode,visitGuid);

        Call<String> call = manager.visitCaseDetailsFormeService(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("下载数据"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    caseDetailBean data = gson.fromJson(body, new TypeToken<caseDetailBean>() {}.getType());
                    caseDetailBean.DataBean bean = data.getData();
                    updataUi(bean);

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        data=new ArrayList();
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        adapter=new visitAdapter(this,data);
    //    lv_visit.setAdapter(adapter);
       //
    }

    /**
     * 得到数据后更新界面UI
     * @param bean
     */
    private void updataUi(caseDetailBean.DataBean bean) {
        tv_debtor_name.setText("欠款人："+bean.getName());
        tv_debtor_money.setText("委案金额："+bean.getCaseTotalAppointAmount()+"元");
        tv_debtor_adress.setText("欠款人地址："+bean.getAddress());
        tv_base_collecta_mount.setText("催收金额："+bean.getCaseTotalUrgeAmount()+"元");
        tv_base_last_arrears.setText("回款金额："+bean.getCaseTotalReceiptAmount()+"元");
        tv_visit_area.setText("外访区域："+bean.getVisitArea());
        tv_visit_goal.setText("外访目标："+bean.getVisitGoal());
        tv_visit_remark.setText("外访备注："+bean.getRemark());
        tv_customer_name.setText("客户名称："+bean.getCustomerName());
        tv_gender.setText("性别："+bean.getGenderText());
        tv_clerk.setText("案件催收员："+bean.getClerkName());
        tv_visitors.setText("外访人员："+bean.getVisitors());
        tv_collect_status.setText("催收状态："+bean.getCaseUrgeStatusText());
        logUtils.d("案件编号："+caseCode);
        tv_visit_caseid.setText("案件编号："+caseCode);
        logUtils.d("批次编号："+bean.getBatchCode());
        tvBatchCode.setText("批次编号："+bean.getBatchCode());
        long deadLine = bean.getAppointBeginDate()/1000;
        String s = unixTime.stampToTime(deadLine);
        logUtils.d("时间转换"+s);
        tv_deadline.setText("退案日期："+s);
        long entrustDate = bean.getAppointEndBeginDate()/1000;
        tv_entrust_date.setText("委案日期："+unixTime.stampToTime(entrustDate));
        long applyTime = bean.getApplyTime()/1000;
        tvAppDate.setText("申请时间："+unixTime.stampToTime(applyTime));
        tvApplicant.setText("申请人："+bean.getApplicantName());
        tv_card_address.setText("开卡地址："+bean.getCardAddress());
        tvVisitStatus.setText("外访状态："+bean.getVisitStatusText());
        logUtils.d("证件号码："+bean.getCidNo());
        tvIdno.setText("证件号码："+bean.getCidNo());
        long lasttime = bean.getLatestFollowUpTime();
        if (lasttime!=0){
            tvLastProcessDate.setText("最后催收时间："+unixTime.stampToTime(lasttime/1000));
        }


    }

    @Override
    protected void initLise() {

    }
}
