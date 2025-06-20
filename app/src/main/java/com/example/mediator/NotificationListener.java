public class NotificationListener extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // Check if notification is from UPI app
        if (sbn.getPackageName().equals("your.upi.app.package")) {
            Notification notification = sbn.getNotification();
            Bundle extras = notification.extras;
            String text = extras.getCharSequence(Notification.EXTRA_TEXT).toString();
            
            // Parse notification text for amount and other details
            processUPINotification(text);
        }
    }
    
    private void processUPINotification(String text) {
        // Extract payment details using regex
        Pattern pattern = Pattern.compile("Paid ₹([0-9,.]+) to");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String amount = matcher.group(1);
            // Launch your mediator activity with this data
            Intent intent = new Intent(this, MediatorActivity.class);
            intent.putExtra("amount", amount);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}