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

    private final int ghostModeRow = 1;
    private final int antiDeleteRow = 2;
    private final int localPremiumRow = 3;

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
        items.add(SettingsHelper.asSwitchCG(ghostModeRow,
                getString(R.string.ZG_GhostMode), getString(R.string.ZG_GhostMode_Desc))
                .setChecked(ZettaConfig.INSTANCE.getGhostMode()));
        items.add(SettingsHelper.asSwitchCG(antiDeleteRow,
                getString(R.string.ZG_AntiDelete), getString(R.string.ZG_AntiDelete_Desc))
                .setChecked(ZettaConfig.INSTANCE.getAntiDelete()));
        items.add(SettingsHelper.asSwitchCG(localPremiumRow,
                getString(R.string.ZG_LocalPremium), getString(R.string.ZG_LocalPremium_Desc))
                .setChecked(ZettaConfig.INSTANCE.getLocalPremium()));
        items.add(UItem.asShadow(null));
    }

    @Override
    protected boolean onLongClick(UItem item, View view, int position, float x, float y) {
        return false;
    }

    @Override
    protected void onClick(UItem item, View view, int position, float x, float y) {
        if (item.id == ghostModeRow) {
            ZettaConfig.INSTANCE.setGhostMode(!ZettaConfig.INSTANCE.getGhostMode());
            SettingsHelper.updateCheckState(view, ZettaConfig.INSTANCE.getGhostMode());
        } else if (item.id == antiDeleteRow) {
            ZettaConfig.INSTANCE.setAntiDelete(!ZettaConfig.INSTANCE.getAntiDelete());
            SettingsHelper.updateCheckState(view, ZettaConfig.INSTANCE.getAntiDelete());
        } else if (item.id == localPremiumRow) {
            ZettaConfig.INSTANCE.setLocalPremium(!ZettaConfig.INSTANCE.getLocalPremium());
            SettingsHelper.updateCheckState(view, ZettaConfig.INSTANCE.getLocalPremium());
        }
    }

}
