package uz.unnarsx.cherrygram.preferences;

import static org.telegram.messenger.LocaleController.getString;

import android.content.Context;
import android.view.View;

import org.telegram.messenger.R;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalFragment;

import java.util.ArrayList;

import uz.unnarsx.cherrygram.core.configs.ZettaConfig;
import uz.unnarsx.cherrygram.preferences.helpers.SettingsHelper;

public class ZettaModificationsPreferencesEntry extends UniversalFragment {

    private final int testFeatureOneRow = 1;
    private final int testFeatureTwoRow = 2;
    private final int testFeatureThreeRow = 3;

    @Override
    protected CharSequence getTitle() {
        return getString(R.string.ZG_Modifications);
    }

    @Override
    public View createView(Context context) {
        setMD3(true);
        return super.createView(context);
    }

    @Override
    protected void fillItems(ArrayList<UItem> items, UniversalAdapter adapter) {
        items.add(UItem.asHeader(getString(R.string.ZG_Modifications)));
        items.add(SettingsHelper.asSwitchCG(testFeatureOneRow, getString(R.string.ZG_TestFeatureOne))
                .setChecked(ZettaConfig.INSTANCE.getTestFeatureOne()));
        items.add(SettingsHelper.asSwitchCG(testFeatureTwoRow, getString(R.string.ZG_TestFeatureTwo))
                .setChecked(ZettaConfig.INSTANCE.getTestFeatureTwo()));
        items.add(SettingsHelper.asSwitchCG(testFeatureThreeRow, getString(R.string.ZG_TestFeatureThree))
                .setChecked(ZettaConfig.INSTANCE.getTestFeatureThree()));
        items.add(UItem.asShadow(null));
    }

    @Override
    protected boolean onLongClick(UItem item, View view, int position, float x, float y) {
        return false;
    }

    @Override
    protected void onClick(UItem item, View view, int position, float x, float y) {
        if (item.id == testFeatureOneRow) {
            ZettaConfig.INSTANCE.setTestFeatureOne(!ZettaConfig.INSTANCE.getTestFeatureOne());
            SettingsHelper.updateCheckState(view, ZettaConfig.INSTANCE.getTestFeatureOne());
        } else if (item.id == testFeatureTwoRow) {
            ZettaConfig.INSTANCE.setTestFeatureTwo(!ZettaConfig.INSTANCE.getTestFeatureTwo());
            SettingsHelper.updateCheckState(view, ZettaConfig.INSTANCE.getTestFeatureTwo());
        } else if (item.id == testFeatureThreeRow) {
            ZettaConfig.INSTANCE.setTestFeatureThree(!ZettaConfig.INSTANCE.getTestFeatureThree());
            SettingsHelper.updateCheckState(view, ZettaConfig.INSTANCE.getTestFeatureThree());
        }
    }

}
