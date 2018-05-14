//product
insertProduct("Pancit Bihon", "cooked food", 35.00, "1 cup per order");
insertProduct("Adobong Manok", "cooked food", 50.00, "3 pieces of meat per order");
insertProduct("Sinigang na Baboy", "cooked food", 50.00, "3 pieces of meat per order");
insertProduct("Plain Rice", "cooked food", 15.00, "1 cup per order");
insertProduct("Nilagang Baka", "cooked food", 50.00, "3 pieces of meat per order");
insertProduct("Pritong Bangus", "cooked food", 45.00, "1 entire fish per order");
insertProduct("Pork Barbecue on a Stick", "cooked food", 15.00, "1 stick per order");
insertProduct("Turon", "sari-sari", 15.00, "");
insertProduct("Cheese Piattos", "sari-sari", 15.00, "");
insertProduct("Oreos", "sari-sari", 12.00, "");
insertProduct("Skyflakes", "sari-sari", 10.00, "");
insertProduct("Nova", "sari-sari", 15.00, "");
insertProduct("Lemon Smart C", "drinks", 15.00, "");
insertProduct("Summit Water", "drinks", 12.00, "");
insertProduct("Gatorade Blue Bolt", "drinks", 30.00, "");

//customer
insertCustomer("Jeff", "T.", "Andawi", 1500.00, 0.00);
insertCustomer("Owen", "S.", "Medina", 1250.00, 0.00);
insertCustomer("Brian", "P.", "Pacia", 1250.00, 0.00);
insertCustomer("Angela", "M.", "Mercado", 1200.00, 100.00);
insertCustomer("Paolo", "O.", "Alilam", 1100.00, 200.00);
insertCustomer("Enzo", "A.", "Orbeta", 1100.00, 150.00);
insertCustomer("Alec", "A.", "Aquino", 1000.00, 300.00);
insertCustomer("Kristi", "T.", "Ingco", 1000.00, 100.00);
insertCustomer("Alyssa", "C.", "Cuan", 750.00, 0.00);
insertCustomer("Jerry", "P.", "Patajo", 500.00, 0.00);
//orders
insertOrder("Alec", "A.", "Aquino", 2018 , 5 , 15 );
insertOrder("Kristi", "T.", "Ingco", 2018 , 5 , 15 );
insertOrder("Enzo", "A.", "Orbeta", 2018 , 5 , 15 );
insertOrder("Paolo", "O.", "Alilam", 2018 , 5 , 16);
insertOrder("Angela", "M.", "Mercado", 2018 , 5 , 16);
insertOrder("Brian," "P.", "Pacia", 2018 , 5 , 17);
insertOrder("Owen", "S.", "Medina", 2018 , 5 , 17);
insertOrder("Jeff", "T.", "Andawi", 2018 , 5 , 17);
insertOrder("Jerry", "P.", "Patajo", 2018 , 5 , 17);
insertOrder("Brian," "P.", "Pacia", 2018 , 5 , 17);

//orderitem
insertOrderItem("Brian", "P.", "Pacia", "Pork Barbecue on a Stick", 4);
insertOrderItem("Brian", "P.", "Pacia", "Turon", 2);
insertOrderItem("Brian", "P.", "Pacia", "Pork Barbecue on a Stick", 2);
insertOrderItem("Owen", "S.", "Medina", "Lemon Smart C", 15);
insertOrderItem("Owen", "S.", "Medina", "Oreos", 10);
insertOrderItem("Jeff", "T.", "Andawi", "Cheese Piattos", 2);
insertOrderItem("Jeff", "T.", "Andawi", "Summit Water", 1);
insertOrderItem("Angela", "M.", "Mercado", "Nilagang Baka", 1);
insertOrderItem("Angela", "M.", "Mercado", "Plain Rice", 2);
insertOrderItem("Paolo", "O.", "Alilam", "Gatorade Blue Bolt", 1);
insertOrderItem("Alec", "A.", "Aquino", "Adobong Manok", 1);
insertOrderItem("Alec", "A.", "Aquino", "Plain Rice", 1);
insertOrderItem("Alec", "A.", "Aquino", "Summit Water", 1);
insertOrderItem("Kristi", "T.", "Ingco", "Pritong Bangus", 3);
insertOrderItem("Enzo", "A.", "Orbeta", "Skyflakes", 10);
insertOrderItem("Jerry", "P.", "Patajo", "Turon", 4);
//inventory
insertBegInv(2018, 5, 15, "Pancit Bihon", 15, 0, 0);
insertBegInv(2018, 5, 15, "Adobong Manok", 15, 0, 0);
insertBegInv(2018, 5, 15, "Sinigang na Baboy", 15, 0, 0);
insertBegInv(2018, 5, 15, "Plain Rice", 15, 0, 0);
insertBegInv(2018, 5, 15, "Nilagang Baka", 15, 0, 0);
insertBegInv(2018, 5, 15, "Pritong Bangus", 15, 0, 0);
insertBegInv(2018, 5, 15, "Pork Barbecue on a Stick", 15, 0, 0);
insertBegInv(2018, 5, 15, "Turon", 15, 0, 0);
insertBegInv(2018, 5, 15, "Cheese Piattos", 15, 0, 0);
insertBegInv(2018, 5, 15, "Oreos", 15, 0, 0);
insertBegInv(2018, 5, 15, "Skyflakes", 15, 0, 0);
insertBegInv(2018, 5, 15, "Nova", 15, 0, 0);
insertBegInv(2018, 5, 15, "Lemon Smart C", 15, 0, 0);
insertBegInv(2018, 5, 15, "Summit Water", 15, 0, 0);
insertBegInv(2018, 5, 15, "Gatorade Blue Bolt", 15, 0, 0);
insertBegInv(2018, 5, 16, "Pancit Bihon", 15, 0, 0);
insertBegInv(2018, 5, 16, "Adobong Manok", 15, 0, 0);
insertBegInv(2018, 5, 16, "Sinigang na Baboy", 15, 0, 0);
insertBegInv(2018, 5, 16, "Plain Rice", 15, 0, 0);
insertBegInv(2018, 5, 16, "Nilagang Baka", 15, 0, 0);
insertBegInv(2018, 5, 16, "Pritong Bangus", 15, 0, 0);
insertBegInv(2018, 5, 16, "Pork Barbecue on a Stick", 15, 0, 0);
insertBegInv(2018, 5, 16, "Turon", 15, 0, 0);
insertBegInv(2018, 5, 16, "Cheese Piattos", 15, 0, 0);
insertBegInv(2018, 5, 16, "Oreos", 15, 0, 0);
insertBegInv(2018, 5, 16, "Skyflakes", 15, 0, 0);
insertBegInv(2018, 5, 16, "Nova", 15, 0, 0);
insertBegInv(2018, 5, 16, "Lemon Smart C", 15, 0, 0);
insertBegInv(2018, 5, 16, "Summit Water", 15, 0, 0);
insertBegInv(2018, 5, 16, "Gatorade Blue Bolt", 15, 0, 0);
insertBegInv(2018, 5, 17, "Pancit Bihon", 15, 0, 0);
insertBegInv(2018, 5, 17, "Adobong Manok", 15, 0, 0);
insertBegInv(2018, 5, 17, "Sinigang na Baboy", 15, 0, 0);
insertBegInv(2018, 5, 17, "Plain Rice", 15, 0, 0);
insertBegInv(2018, 5, 17, "Nilagang Baka", 15, 0, 0);
insertBegInv(2018, 5, 17, "Pritong Bangus", 15, 0, 0);
insertBegInv(2018, 5, 17, "Pork Barbecue on a Stick", 15, 0, 0);
insertBegInv(2018, 5, 17, "Turon", 15, 0, 0);
insertBegInv(2018, 5, 17, "Cheese Piattos", 15, 0, 0);
insertBegInv(2018, 5, 17, "Oreos", 15, 0, 0);
insertBegInv(2018, 5, 17, "Skyflakes", 15, 0, 0);
insertBegInv(2018, 5, 17, "Nova", 15, 0, 0);
insertBegInv(2018, 5, 17, "Lemon Smart C", 15, 0, 0);
insertBegInv(2018, 5, 17, "Summit Water", 15, 0, 0);
insertBegInv(2018, 5, 17, "Gatorade Blue Bolt", 15, 0, 0);

