package gt.umg.ventasonline;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gt.umg.ventasonline.Common.Utils;
import gt.umg.ventasonline.localDb.ConfigurationDb;

public class ConfiguracionActivity extends Activity {

    private EditText configurationUrlWs;
    private Button configurationSaveButton;
    private ConfigurationDb configurationDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        configurationUrlWs = (EditText) findViewById(R.id.configuration_url_ws);
        configurationSaveButton = (Button) findViewById(R.id.configuration_save_button);

        configurationSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConfiguration();
            }
        });

        configurationDb = new ConfigurationDb(this);

        try {
            configurationUrlWs.setText(configurationDb.getConfigurationByName("WS_URL"));
        } catch (Exception exception) {
            Utils.showCustomMessage(1, exception.getMessage(), this);
        }

    }

    private void saveConfiguration() {
        try {

            if (configurationUrlWs.getText().toString() == null || "".equals(configurationUrlWs.getText().toString())) {
                return;
            }

            configurationDb.updateConfiguration("WS_URL", configurationUrlWs.getText().toString().trim());

            Intent intent = new Intent(ConfiguracionActivity.this, LoginActivity.class);
            startActivity(intent);
            this.finish();

        } catch (Exception exception) {
            Utils.showCustomMessage(1, exception.getMessage(), this);
        }
    }
}