public class MediatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediator);
        
        String amount = getIntent().getStringExtra("amount");
        
        // Show dialog asking to add to money manager
        new AlertDialog.Builder(this)
            .setTitle("Add to Money Manager?")
            .setMessage("You paid " + amount + ". Add this transaction?")
            .setPositiveButton("Yes", (dialog, which) -> {
                launchMoneyManager(amount);
            })
            .setNegativeButton("No", (dialog, which) -> {
                finish();
            })
            .show();
    }
    
    private void launchMoneyManager(String amount) {
        try {
            // Create intent for your money manager's add transaction activity
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(
                "com.your.money.manager.package",
                "com.your.money.manager.package.AddTransactionActivity"
            ));
            
            // Pass the amount as extra
            intent.putExtra("amount", amount);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Money Manager not installed", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}