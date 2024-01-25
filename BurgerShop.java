import java.util.*;

class Orders{
	
	private String orderID;
	private String phoneNumber;
	private String customerName;
	private int orderStatus;
	private int orderQuantity;
	private double orderValue;
	
	/*
	String orderID;
	String phoneNumber;
	String customerName;
	int orderStatus;
	int orderQuantity;
	double orderValue;
	*/
	public void setOrderID(String orderID){
		this.orderID = orderID;
	}
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	public void setOrderStatus(int orderStatus){
		this.orderStatus = orderStatus;
	}
	public void setOrderQuantity(int orderQuantity){
		this.orderQuantity = orderQuantity;
	}
	public void setOrderValue(double orderValue){
		this.orderValue = orderValue;
	}
	
	
	public String getOrderID(){
		return orderID;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public String getCustomerName(){
		return customerName;
	}
	public int getOrderStatus(){
		return orderStatus;
	}
	public int getOrderQuantity(){
		return orderQuantity;
	}
	public double getOrderValue(){
		return orderValue;
	}
	
	
	 final static double BURGERPRICE = 500;
	
	 // Order status
    public static final int CANCEL = 0;
    public static final int PREPARING = 1;
    public static final int DELIVERED = 2;
	
	
}

class BurgerShop {
	
	private static Orders[] orders = new Orders[0];
	private static Orders obj1 = new Orders();
	

	/*
    // Order Array
    public static String[] orderIdArray = { "B0001", "B0002", "B0003", "B0004", "B0005", "B0006", "B0007", "B0008", "B0009", "B0010", "B0011", "B0012", "B0013", "B0014", "B0015B0016" };
    // Customer Arrays
    public static String[] orderCustomerArray = { "0701111111", "0777777777", "0342222222", "0777777777", "0386677676", "0715455465", "0709353956", "0724565562", "0715455465", "0342222222", "0342233223", "0755080123", "0715994936", "0715994940", "0745994967" };
    public static String[] customerNameArray = { "Amali", "Shehan", "Kasun", "Shehan", "Chathura", "Ruwan", "Rishmi", "Gihan", "Ruwan", "Dilshan", "Hasindu", "Eshan", "Ravindu", "Harshana", "Pasindu", };
    public static int[] orderStatusArray = { 2, 1, 2, 2, 1, 1, 0, 2, 1, 1, 2, 2, 1, 0, 2 };
    public static int[] orderQTYArray = { 1, 2, 4, 2, 1, 1, 3, 6, 5, 2, 7, 3, 2, 2, 1 };
    public static double[] orderValueArray = { 500, 1000, 2000, 1000, 500, 500, 1500, 3000, 2500, 1000, 3500, 1500, 1000, 1000, 500 };
	*/

    // console clear
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    // validation Customer ID
    public static boolean validationcustomerId(String customerId) {
        if (customerId.length() == 10) {
            if (customerId.startsWith("0")) {
                try {
                    int i = Integer.parseInt(customerId);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }

    // generate order Id
    public static String generateOrderId() {
		if (orders.length==0){
			return "B0001";
		}
        String lastOrderId = orders[orders.length -1].getOrderID(); //orderIdArray[orderIdArray.length - 1];
        int x = Integer.parseInt(lastOrderId.substring(1)); //1
        int temp = ++x; 
        int count = 0; 
        while (temp != 0) {
            count++;
            temp /= 10; //temp = temp/10;
        }
        switch (count) {
            case 1:
                return "B000" + x;
            case 2:
                return "B00" + x;
            case 3:
                return "B0" + x;
            default:
                return "B" + x;
        }
        /*
        if(orders.length==0){
			return "B0001";
		}
			lastOrderId = orders[orders.length -1].orderID;
			int number = Integer.parseInt(lastOrderId.split("B")[1]); //1
			number++;//2
			return String.format("B%04d",number); //printf("",) //B0002
		*/
    }

    // placeOrder
    public static void placeOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tPLACE ORDER\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n\n");
        System.out.print("ORDER ID - ");
        String orderId = generateOrderId();
        System.out.println(orderId + "\n================\n\n");

        L1: do {
            System.out.print("Enter Customer ID (phone no.): ");
            String customerId = input.next();
            if (customerId.charAt(0)!='0' || customerId.length()!=10){
				continue L1;
			}
            boolean isExistCustomer = false;
            String customerName = "";
            for (int i = 0; i < orders.length; i++) {
                if (customerId.equals(orders[i].getPhoneNumber())) {
                    isExistCustomer = true;
                    System.out.println("Enter Customer Name: " + orders[i].getCustomerName());
                    customerName = orders[i].getCustomerName();
                    break;
                }
            }
            if (!isExistCustomer) {
                System.out.print("\nEnter Customer Name: ");
                customerName = input.next();
            }
            System.out.print("Enter Burger Quantity - ");
            int qty = input.nextInt();
            if (qty > 0) {
                double billValue = qty * obj1.BURGERPRICE;
                System.out.printf("Total value - %.2f", billValue);
                System.out.println();
                L3: do {
                    System.out.print("\tAre you confirm order - ");
                    String option = input.next().toUpperCase();
                    if (option.equalsIgnoreCase("Y")) {
                        int size = orders.length + 1;

                        Orders [] tempOrders = new Orders[size];
                        
                        for (int i = 0; i < orders.length; i++){
                            tempOrders[i] = orders[i];

                        }
                        orders = tempOrders;

                        orders[orders.length-1] = new Orders();

                        orders[orders.length-1].setOrderID(orderId);
                        orders[orders.length-1].setCustomerName(customerName);
						orders[orders.length-1].setOrderStatus(1);
                        orders[orders.length-1].setPhoneNumber(customerId);
                        orders[orders.length-1].setOrderQuantity(qty);
                        orders[orders.length-1].setOrderValue(billValue);
                        System.out.println("\n\tYour order is enter to the system successfully...");
                        break L1;
                    } else if (option.equalsIgnoreCase("N")) {
                        System.out.println("\n\tYour order is not enter the system...");
                        clearConsole();
                        return;
                    } else {
                        System.out.println("\tInvalid option..input again...");
                        break L1;
                    }
                } while (true);
            }
            // }
        } while (true);
        L4: do {
            System.out.println();
            System.out.print("Do you want to place another order (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                placeOrder();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L4;
            }
        } while (true);

    }

    // Search best customer
    public static void searchBestCustomer() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tBEST Customer\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");

        Orders [] sortOrders = new Orders[0];

        for (int i = 0; i < orders.length; i++) {
            boolean isExist = false;
            for (int j = 0; j < sortOrders.length; j++) {
                if (sortOrders[j].getPhoneNumber().equals(orders[i].getPhoneNumber())) {
                    if (orders[i].getOrderStatus()!= obj1.CANCEL){
						sortOrders[j].setOrderValue(sortOrders[j].getOrderValue() + orders[i].getOrderValue());
					}
                    isExist = true;
                }
            }
            if (!isExist) {

                Orders [] tempSortOrders = new Orders[sortOrders.length + 1];
                
                for (int j = 0; j < sortOrders.length; j++) {
					tempSortOrders[j] = new Orders();
                    tempSortOrders[j].setPhoneNumber(sortOrders[j].getPhoneNumber());
                    tempSortOrders[j].setCustomerName(sortOrders[j].getCustomerName());
                    tempSortOrders[j].setOrderValue(sortOrders[j].getOrderValue());
                }
                sortOrders = tempSortOrders;
          
                sortOrders[sortOrders.length-1] = new Orders();
                

                sortOrders[sortOrders.length -1].setPhoneNumber(orders[i].getPhoneNumber());
                sortOrders[sortOrders.length -1].setCustomerName(orders[i].getCustomerName());
                sortOrders[sortOrders.length -1].setOrderValue(orders[i].getOrderValue());
                
            }
            //---------------------------------------------------------------------------------------------
        }
        // sort
        for (int i = 1; i < sortOrders.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sortOrders[j].getOrderValue() < sortOrders[i].getOrderValue()) {
                    String temp = sortOrders[j].getPhoneNumber();
                    sortOrders[j].setPhoneNumber(sortOrders[i].getPhoneNumber());
                    sortOrders[i].setPhoneNumber(temp);
                    temp = sortOrders[j].getCustomerName();
                    sortOrders[j].setCustomerName(sortOrders[i].getCustomerName());
                    sortOrders[i].setCustomerName(temp);
                    double tempd = sortOrders[j].getOrderValue();
                    sortOrders[j].setOrderValue(sortOrders[i].getOrderValue());
                    sortOrders[i].setOrderValue(tempd);
                }
            }
        }
        System.out.println("\n----------------------------------------");
        String line1 = String.format("%-14s%-15s%8s", " CustomerID", "Name", "Total");
        System.out.println(line1);
        System.out.println("----------------------------------------");
        for (int i = 0; i < sortOrders.length; i++) {

            String line = String.format("%1s%-14s%-15s%8.2f", " ", sortOrders[i].getPhoneNumber(), sortOrders[i].getCustomerName(), sortOrders[i].getOrderValue());
            System.out.println(line);
            System.out.println("----------------------------------------");

        }
        L: do {
            Scanner input = new Scanner(System.in);
            System.out.print("\n\tDo you want to go back to main menu? (Y/N)> ");
            String exitOption = input.nextLine();
            if (exitOption.equalsIgnoreCase("Y")) {
                clearConsole();
                homePage();
            } else if (exitOption.equalsIgnoreCase("N")) {
                clearConsole();
                searchBestCustomer();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L;
            }
        } while (true);

    }

    // search order
    public static void searchOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tSEARCH ORDER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        L1: do {
            System.out.print("Enter order Id - ");
            String orderId = input.next();
            System.out.println();
            for (int i = 0; i < orders.length; i++) {
                if (orderId.equals(orders[i].getOrderID())) {
                    // int j=search(orderCustomerArray[i]);
                    String status = "";
                    switch (orders[i].getOrderStatus()) {
                        case Orders.CANCEL:
                            status = "Cancel";
                            break;
                        case Orders.PREPARING:
                            status = "Preparing";
                            break;
                        case Orders.DELIVERED:
                            status = "Delivered";
                            break;
                    }
                    System.out.println("---------------------------------------------------------------------------");
                    String line1 = String.format("%-10s%-14s%-12s%-10s%-14s%-10s", " OrderID", " CustomerID", " Name",
                            "Quantity", "  OrderValue", "  OrderStatus");
                    System.out.print(line1);
                    System.out.println(" |");
                    System.out.println("---------------------------------------------------------------------------");
                    String line = String.format("%1s%-10s%-14s%-15s%-10d%-14.2f%-10s", " ", orders[i].getOrderID(),
                            orders[i].getPhoneNumber(), orders[i].getCustomerName(), orders[i].getOrderQuantity(), orders[i].getOrderValue(), status);
                    System.out.print(line);
                    System.out.println("|");
                    System.out.println("---------------------------------------------------------------------------");
                    break L1;
                }
            }
            L2: do {
                System.out.print("\n\nInvalid Order ID. Do you want to enter again? (Y/N)>");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    clearConsole();
                    searchOrder();
                } else if (option.equalsIgnoreCase("N")) {
                    clearConsole();
                    return;
                } else {
                    System.out.println("\tInvalid option..input again...");
                    continue L2;
                }
            } while (true);
        } while (true);
        L3: do {
            System.out.println();
            System.out.print("Do you want to search another order details (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                searchOrder();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L3;
            }
        } while (true);
    }

    // search Customer
    public static void searchCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tSEARCH CUSTOMER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        L1: do {
            System.out.print("Enter customer Id - ");
            String customerId = input.next();
            System.out.println("\n");
            for (int i = 0; i < orders.length; i++) {
                if (customerId.equals(orders[i].getPhoneNumber())) {
                    System.out.println("CustomerID - " + orders[i].getPhoneNumber());
                    System.out.println("Name       - " + orders[i].getCustomerName());
                    System.out.println("\nCustomer Order Details");
                    System.out.println("========================\n");
                    System.out.println("----------------------------------------------");
                    String line = String.format("%-12s%-18s%-14s", " Order_ID", "Order_Quantity", "Total_Value  ");
                    System.out.println(line);
                    System.out.println("----------------------------------------------");
                    for (int j = 0; j < orders.length; j++) {
                        if (orders[j].getPhoneNumber().equals(customerId)) {
                            String line2 = String.format("%1s%-12s%-18d%-14.2f", " ", orders[j].getOrderID(), orders[j].getOrderQuantity(),
                                    orders[j].getOrderValue());
                            System.out.println(line2);
                        }
                    }
                    System.out.println("----------------------------------------------");
                    break L1;
                }

            }
            L2: do {
                System.out.print("\n\nInvalid Customer ID. Do you want to enter again? (Y/N)>");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    clearConsole();
                    searchCustomer();
                } else if (option.equalsIgnoreCase("N")) {
                    clearConsole();
                    return;
                } else {
                    System.out.println("\tInvalid option..input again...");
                    continue L2;
                }
            } while (true);
        } while (true);
        L3: do {
            System.out.println();
            System.out.print("Do you want to search another customer details (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                searchCustomer();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L3;
            }
        } while (true);
    }

    // View Order list
    public static void viewOrders() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tVIEW ORDER LIST\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("[1] Delivered Order");
        System.out.println("[2] Preparing Order");
        System.out.println("[3] Cancel Order");

        System.out.print("\nEnter an option to continue > ");
        int option = input.nextInt();
        switch (option) {
            case 1:
                clearConsole();
                deliverOrder();
                break;
            case 2:
                clearConsole();
                preparingOrder();
                break;
            case 3:
                clearConsole();
                cancelOrder();
                break;
        }
    }

    // Delivered Order
    public static void deliverOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tDELIVERED ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity",
                "  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getOrderStatus() == Orders.DELIVERED) {
                // int j=search(orderCustomerArray[i]);
                String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", orders[i].getOrderID(), orders[i].getPhoneNumber(),
                        orders[i].getCustomerName(), orders[i].getOrderQuantity(), orders[i].getOrderValue());
                System.out.println(line);
                System.out.println("--------------------------------------------------------------");
            }
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                deliverOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }

    // Preparing Order
    public static void preparingOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tPREPARING ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity",
                "  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getOrderStatus() == Orders.PREPARING) {
                // int j=search(orderCustomerArray[i]);
                String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", orders[i].getOrderID(), orders[i].getPhoneNumber(),
                        orders[i].getCustomerName(), orders[i].getOrderQuantity(), orders[i].getOrderValue());
                System.out.println(line);
                System.out.println("--------------------------------------------------------------");
            }
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                preparingOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }

    // Cancel Order
    public static void cancelOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tCANCEL ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity",
                "  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getOrderStatus() == Orders.CANCEL) {
                // int j=search(orderCustomerArray[i]);
                
                  String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", orders[i].getOrderID(), orders[i].getPhoneNumber(),
                        orders[i].getCustomerName(), orders[i].getOrderQuantity(), orders[i].getOrderValue());      
                System.out.println(line);
                System.out.println("--------------------------------------------------------------");
            }
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                cancelOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }

    // Update order details
    public static void updateOrderDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tUPDATE ORDER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        L1: do {
            System.out.print("Enter order Id - ");
            String orderId = input.next();
            System.out.println();
            for (int i = 0; i < orders.length; i++) {
                if (orderId.equals(orders[i].getOrderID())) {
                    // int j=search(orderCustomerArray[i]);
                    String status = "";
                    switch (orders[i].getOrderStatus()) {
                        case 0:
                            status = "Cancel";
                            break;
                        case 1:
                            status = "Preparing";
                            break;
                        case 2:
                            status = "Delivered";
                            break;
                    }
                    if (status == "Cancel") {
                        System.out.println("This Order is already cancelled...You can not update this order...");
                    } else if (status == "Delivered") {
                        System.out.println("This Order is already delivered...You can not update this order...");
                    } else {
                        System.out.println("OrderID    - " + orders[i].getOrderID());
                        System.out.println("CustomerID - " + orders[i].getPhoneNumber());
                        System.out.println("Name       - " + orders[i].getCustomerName());
                        System.out.println("Quantity   - " + orders[i].getOrderQuantity());
                        System.out.printf("OrderValue - %.2f", orders[i].getOrderValue());
                        System.out.println("\nOrderStatus- " + status);

                        System.out.println("\nWhat do you want to update ? ");
                        System.out.println("\t(01) Quantity ");
                        System.out.println("\t(02) Status ");
                        System.out.print("\nEnter your option - ");
                        int option = input.nextInt();
                        switch (option) {
                            case 1:
                                clearConsole();
                                System.out.println("\nQuantity Update");
                                System.out.println("================\n");
                                System.out.println("OrderID    - " + orders[i].getOrderID());
                                System.out.println("CustomerID - " + orders[i].getPhoneNumber());
                                System.out.println("Name       - " + orders[i].getCustomerName());
                                System.out.print("\nEnter your quantity update value - ");
                                int qty = input.nextInt();
                                orders[i].setOrderQuantity(qty);
                                orders[i].setOrderValue(qty * Orders.BURGERPRICE);
                                System.out.println("\n\tupdate order quantity success fully...");
                                System.out.println("\nnew order quantity - " + orders[i].getOrderQuantity());
                                System.out.printf("new order value - %.2f", orders[i].getOrderValue());
                                break;
                            case 2:
                                clearConsole();
                                System.out.println("\nStatus Update");
                                System.out.println("==============\n");
                                System.out.println("OrderID    - " + orders[i].getOrderID());
                                System.out.println("CustomerID - " + orders[i].getPhoneNumber());
                                System.out.println("Name       - " + orders[i].getCustomerName());
                                System.out.println("\n\t(0)Cancel");
                                System.out.println("\t(1)Preparing");
                                System.out.println("\t(2)Delivered");
                                System.out.print("\nEnter new order status - ");
                                int s = input.nextInt();
                                orders[i].setOrderStatus(s);
                                switch (orders[i].getOrderStatus()) {
                                    case 0:
                                        status = "Cancel";
                                        break;
                                    case 1:
                                        status = "Preparing";
                                        break;
                                    case 2:
                                        status = "Delivered";
                                        break;
                                }
                                System.out.println("\n\tUpdate order status successfully...");
                                System.out.println("\nnew order status - " + status);
                                break;
                        }
                    }
                    break L1;
                }
            }
            L3: do {
                System.out.print("\n\nInvalid Order ID. Do you want to enter again? (Y/N)>");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    System.out.println("\n");
                    continue L1;
                } else if (option.equalsIgnoreCase("N")) {
                    clearConsole();
                    return;
                } else {
                    System.out.println("\tInvalid option..input again...");
                    continue L3;
                }
            } while (true);
        } while (true);
        L3: do {
            System.out.println();
            System.out.print("Do you want to update another order details (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                clearConsole();
                updateOrderDetails();
            } else if (option.equalsIgnoreCase("N")) {
                clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L3;
            }
        } while (true);
    }

    // exit
    public static void exit() {
        clearConsole();
        System.out.println("\n\t\tYou left the program...\n");
        System.exit(0);
    }

    // home page
    public static void homePage() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tiHungry Burger\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("[1] Place Order\t\t\t[2] Search Best Customer");
        System.out.println("[3] Search Order\t\t[4] Search Customer");
        System.out.println("[5] View Orders\t\t\t[6] Update Order Details");
        System.out.println("[7] Exit");

        Scanner input = new Scanner(System.in);
        do {

            System.out.print("\nEnter an option to continue > ");
            char option = input.next().charAt(0);

            switch (option) {
                case '1':
                    clearConsole();
                    placeOrder();
                    break;
                case '2':
                    clearConsole();
                    searchBestCustomer();
                    break;
                case '3':
                    clearConsole();
                    searchOrder();
                    break;
                case '4':
                    clearConsole();
                    searchCustomer();
                    break;
                case '5':
                    clearConsole();
                    viewOrders();
                    break;
                case '6':
                    clearConsole();
                    updateOrderDetails();
                    break;
                case '7':
                    exit();
                    break;
            }
        } while (true);
    }

    public static void main(String args[]) {
        homePage();
    }

}

