package com.apporiotaxi.techbangla.coinconvert.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apporiotaxi.techbangla.coinconvert.R;
import com.apporiotaxi.techbangla.coinconvert.model.Coin;
import com.apporiotaxi.techbangla.coinconvert.activity.Common;
import com.apporiotaxi.techbangla.coinconvert.remote.CoinService;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CoinService mService;
    RadioButton coin2coin,moneyt2coin,coin2money;
    MaterialSpinner fromSpinner,toSpinner;
    RadioGroup radioGroup;
    Button btnConvert;
    ImageView coinImage;
    String money[]={"USD","GBP","EUR"};
    String coin[]={"BTC","ETC","ETH","LTC","DASH","MAID","XEM","AUR","XMR","XRP"};
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService= Common.getCoinService();
        fromSpinner=(MaterialSpinner)findViewById(R.id.fromSpinner);
        toSpinner=(MaterialSpinner)findViewById(R.id.toSpinner);
        btnConvert=(Button)findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateValue();
            }
        });
        textView=(TextView)findViewById(R.id.toTextView) ;
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.coin2coin){
                    setCoint2CoinSource();
                }
                if(checkedId == R.id.money2coin){
                    setMoney2CointSource();
                }
                if(checkedId == R.id.coin2money){
                    setCoint2MoneySource();
                }
            }
        });
        coin2coin=(RadioButton)findViewById(R.id.coin2coin);
        moneyt2coin=(RadioButton)findViewById(R.id.money2coin);
        coin2money=(RadioButton)findViewById(R.id.coin2money);

        coinImage=(ImageView)findViewById(R.id.coinImage);

        loadCoinlist();
    }

    private void loadCoinlist() {
        if(coin2money.isSelected()){
            setCoint2MoneySource();
        }
        else if(coin2coin.isSelected()){
            setCoint2CoinSource();
        }
        else if(moneyt2coin.isSelected()){
            setMoney2CointSource();
        }
    }

    private void setCoint2MoneySource() {
        fromSpinner.setItems(coin);
        toSpinner.setItems(money);
    }

    private void setMoney2CointSource() {
        fromSpinner.setItems(money);
        toSpinner.setItems(coin);
    }

    private void setCoint2CoinSource() {
        fromSpinner.setItems(coin);
        toSpinner.setItems(coin);
    }

    private void calculateValue() {
        final ProgressDialog mDialog=new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Please Waiting...");
        mDialog.show();

        final String CoinToName=toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString();
        String CoinFromName=fromSpinner.getItems().get(fromSpinner.getSelectedIndex()).toString();
        mService.calculateValue(CoinFromName,CoinToName).enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                mDialog.dismiss();
                if(CoinToName.equals("BTC")){
                    showData(response.body().getBTC());
                }
                else if(CoinToName.equals("ETC")){
                    showData(response.body().getETC());
                }
                else if(CoinToName.equals("ETH")){
                    showData(response.body().getETH());
                }
                else if(CoinToName.equals("XEM")){
                    showData(response.body().getXEM());
                }
                else if(CoinToName.equals("XRP")){
                    showData(response.body().getXRP());
                }
                else if(CoinToName.equals("XMR")){
                    showData(response.body().getXMR());
                }
                else if(CoinToName.equals("LTC")){
                    showData(response.body().getLTC());
                }
                else if(CoinToName.equals("AUR")){
                    showData(response.body().getAUR());
                }
                else if(CoinToName.equals("DASH")){
                    showData(response.body().getDASH());
                }
                else if(CoinToName.equals("MAID")){
                    showData(response.body().getMAID());
                }
                else if(CoinToName.equals("USD")){
                    showData(response.body().getUSD());
                }
                else if(CoinToName.equals("EUR")){
                    showData(response.body().getEUR());
                }
                else if(CoinToName.equals("GRP")){
                    showData(response.body().getGBP());
                }
            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {
                Log.e("ERROR",t.getMessage());
                mDialog.dismiss();
            }
        });
    }

    private void showData(String Value) {
        if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("BTC")){
            Picasso.with(MainActivity.this).load(Common.BTC_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("ETC")){
            Picasso.with(MainActivity.this).load(Common.ETC_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("ETH")){
            Picasso.with(MainActivity.this).load(Common.ETH_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XMR")){
            Picasso.with(MainActivity.this).load(Common.XMR_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XRP")){
            Picasso.with(MainActivity.this).load(Common.XRP_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XEM")){
            Picasso.with(MainActivity.this).load(Common.XEM_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("DASH")){
            Picasso.with(MainActivity.this).load(Common.DASH_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("MAID")){
            Picasso.with(MainActivity.this).load(Common.MAID_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("AUR")){
            Picasso.with(MainActivity.this).load(Common.AUR_IMAGE).into(coinImage);
            textView.setText(Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("LTC")){
            Picasso.with(MainActivity.this).load(Common.LTC_IMAGE).into(coinImage);
            textView.setText(Value);
        }

        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("USD")){

            textView.setText("$ "+Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("GBP")){

            textView.setText("& "+Value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("EUR")){

            textView.setText("& "+Value);
        }
    }
}
