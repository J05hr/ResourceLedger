package codes.j0sh.resourceledger.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import codes.j0sh.resourceledger.R;
import codes.j0sh.resourceledger.InventoryListingActivity;
import codes.j0sh.resourceledger.ItemActivity;
import codes.j0sh.resourceledger.ItemFormActivity;
import codes.j0sh.resourceledger.objects.Item;
import codes.j0sh.resourceledger.utils.DatabaseHandler;

public class DeleteDialog extends Dialog {
    private Activity activity;
    private TextView dialogTitle;
    private TextView dialogPrompt;
    private Button cancelButton;
    private Button deleteButton;
    private Item selectedItem = null;
    private DatabaseHandler dbHandler;
    private int state;
    private int userType;

    public DeleteDialog(Activity activity, int userType) {
        super(activity);
        this.activity = activity;
        this.userType = userType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_delete);

        // Initialize
        dbHandler = new DatabaseHandler(getContext());
        state = -1;
        dialogTitle = (TextView) findViewById(R.id.dialog_delete_title);
        dialogPrompt = (TextView) findViewById(R.id.dialog_delete_prompt);
        cancelButton = (Button) findViewById(R.id.delete_cancel_button);
        deleteButton = (Button) findViewById(R.id.delete_button);

        if (activity instanceof ItemActivity || activity instanceof ItemFormActivity) {
            dialogTitle.setText("Delete Item");
            if (activity instanceof ItemActivity) {
                selectedItem = ((ItemActivity) activity).getSelectedItem();
            }
            else if (activity instanceof ItemFormActivity) {
                selectedItem = ((ItemFormActivity) activity).getSelectedItem();
            }
            if (selectedItem != null) {
                state = 0;
                dialogPrompt.setText("Are you sure you would like to delete " + selectedItem.getName() + "?");
            }
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity instanceof ItemActivity) {
                    ((ItemActivity) activity).getDeleteDialog().dismiss();
                }
                else if (activity instanceof ItemFormActivity) {
                    ((ItemFormActivity) activity).getDeleteDialog().dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Note that this dialog is used for items, categories, and users
                // So to differentiate, use instanceof on the activity variable
                // Example on line 41
                if (state == 0) {
                    dbHandler.deleteItem(selectedItem);
                    activity.finish();
                    Intent intent = new Intent(getContext(), InventoryListingActivity.class);
                    intent.putExtra("USER_TYPE", userType);
                    activity.startActivity(intent);
                }
            }
        });
    }
}
