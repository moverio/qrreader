package eitc.epsondevelopers.com.qrreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scanButton;
    private TextView qrTextView;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button) findViewById(R.id.scanButton);
        qrTextView = (TextView) findViewById(R.id.qrTextView);
        qrScan = new IntentIntegrator(this);
        scanButton.setOnClickListener(this);
    }

    // Get scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null)
                qrTextView.setText("Result Not Found");
            else
                qrTextView.setText(result.getContents());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
    }
}
