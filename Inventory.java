import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Inventory {

    private ArrayList<Records> masterRecord;
    private ArrayList<Records> records;

    static Scanner sc = new Scanner(System.in);
    double netProfit = 0;
    int counter = 0;

    public Inventory() {
        masterRecord = new ArrayList<>();
        records = new ArrayList<>();
        records.add(new Records("Pencil", "Buy", 10, 5, 100));
        records.add(new Records("Pen", "Buy", 20, 10, 50));
        UpdateMasterRecord(records);
        updateProfit();
    }


    private void UpdateMasterRecord(ArrayList<Records> records) {
        Records temp;
        for (int i = 0; i < records.size(); i++) {
            temp = records.get(i);
            masterRecord.add(new Records(temp.getName(), temp.getType(), temp.getSellingPrice(), temp.getPurchasePrice(), temp.getQuantity()));
        }
    }

   public void updateProfit() {
        Records temp;
        for (int i = counter; i < records.size(); i++) {
            temp = records.get(i);
            if (temp.getType().equals("Buy")) {
                netProfit -= (temp.getPurchasePrice() * temp.getQuantity());
            } else{
                netProfit += (temp.getSellingPrice() * temp.getQuantity());
            }
            counter++;
        }
    }

    private ArrayList<Records> purchase(boolean isSale) {
        ArrayList<Records> temp = new ArrayList<>();
        String name, type;
        double sellingPrice, purchasePrice;
        int quantity;
        while (true) {
            System.out.print("Enter Name (If done with Order enter 0 in place of name):");
            name = sc.next();
            if (name.equals("0")) {
                break;
            }
            System.out.print("Enter Quantity ");
            quantity = sc.nextInt();
            System.out.print("Enter Selling Price: ");
            sellingPrice = sc.nextDouble();
            System.out.print("Enter Purchase Price : ");
            purchasePrice = sc.nextDouble();
            if (isSale) {
                type = "Sell";
            } else {
                type = "Buy";
            }
            temp.add(new Records(name, type, sellingPrice, purchasePrice, quantity));
        }
        return temp;
    }
    public void purchaseValidator(){
        ArrayList<Records> temp;
        boolean isSale = false;
        System.out.println("Is it a sale?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter the choice code : ");
        int choice = sc.nextInt();
        if (choice == 1){
            isSale = true;
        }
        temp = purchase(isSale);
        System.out.println("Approve this order?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter the choice code : ");
        choice = sc.nextInt();
        if (choice == 1){
            UpdateMasterRecord(temp);
            UpdateRecords(temp, isSale);
            updateProfit();
        }
        else {
            System.out.println("Your order has been cancelled");
        }
    }
    public void displayRecords(){
        Records temp;
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Sr no.\t\t  Date\t\t\t\tParticular\t\t\t\tQuantity\t\t\t\tRate\t\t\t  Type");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < records.size(); i++) {
            temp = records.get(i);
            System.out.println(" "+(i+1)+"\t\t  "+temp.getDatetime().format(DateTimeFormatter.ISO_LOCAL_DATE)+"\t\t\t  "+temp.getName()+"\t\t\t\t\t "+temp.getQuantity()+"\t\t\t"+temp.getSellingPrice()+"\t\t\t  "+temp.getType());
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
    }
    public boolean UpdateRecords(ArrayList<Records> records, boolean isSale){
        Records temp1;
        Records temp2;
        boolean updated = false;
        for (int i = 0; i < this.records.size(); i++) {
            for (int j = 0; j < records.size(); j++) {
                temp1 = this.records.get(i);
                temp2 = records.get(j);
                if (temp1.getName().equals(temp2.getName().toUpperCase())){
                    if (!isSale){
                        temp1.setQuantity(temp1.getQuantity() + temp2.getQuantity());
                        temp1.setDateTime(LocalDateTime.now());
                        updated = true;
                    }else {
                        temp1.setQuantity(temp1.getQuantity() - temp2.getQuantity());
                        temp1.setDateTime(LocalDateTime.now());
                        updated = true;
                    }

                }
            }

        }
        return updated;
    }

 public void RunApp()
 {
     int choice;
     do {
         System.out.println("1.Display Records");
         System.out.println("2.Purchase Order");
         System.out.println("3.Show Profit ");
         System.out.println("4.Exit");
         System.out.println("Enter your choice code");
         choice = sc.nextInt();
         switch(choice)
         {
             case 1:
                 displayRecords();
                 break;
             case 2:
                 purchaseValidator();
                 break;
             case 3:
                 System.out.println("Net Profit :" +netProfit);
                 break;
             case 4:
                 break;
         }

     }while(choice!=4);

 }
}