/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestXInterface;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
//Libraries to import for generate Graphics bill
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

//For date and time
 import java.util.Date;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
import javax.swing.table.TableColumn;

/**
 *
 * @author shibayanmondal
 */
public class CustomerPortal extends javax.swing.JFrame {

    
    int pX,pY;
    DefaultTableModel availTable, menuTable, billTable;
    
    PreparedStatement p_st;
    ResultSet rs;
    
    // Base height var of bill
    Double baseHeight=0.0;
    // Creating arraylist with filed of jTable_selected_item
    ArrayList<String> item_selected;
    ArrayList<String> qty_selected;
    ArrayList<String> net_amt;
    
    public CustomerPortal() {
        initComponents();
        this.setSize(1015, 508);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        addDataToTableAvail();
        // Make the text of the table ineditable
        jTable_avl_itm.setDefaultEditor(Object.class, null);
        jTable_menu.setDefaultEditor(Object.class, null);
        jTable_selected_item.setDefaultEditor(Object.class, null);
        
        int width = jTable_avl_itm.getWidth();
        System.out.println(width);
        setColumnWidth(jTable_avl_itm, 0, 100);
        setColumnWidth(jTable_avl_itm, 1, 140);
        setColumnWidth(jTable_avl_itm, 2, 84);
        
        jTable_avl_itm.setRowHeight(30);
        itemComboBox();
        
    }
    
    public void setColumnWidth(JTable table, int col, int width) {
        TableColumn colm = null;
        colm = table.getColumnModel().getColumn(col);
        colm.setPreferredWidth(width);
    }
    
    public String dateTime() {
        DateFormat dt_tm = new SimpleDateFormat("dd-MMM-YYYY    hh:mm:ss aa");
        Date date_time_now = new Date();
        return (dt_tm.format(date_time_now));
    }
    
    private void addDataToTableAvail () {
        
        // Creating a select query to check the existence of username and password in the database
        String query = "SELECT `category` AS 'Category', `menu_item` AS 'Items', `quantity` AS 'QTY.' FROM `item_avail`";
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery(query);
            jTable_avl_itm.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.toString());
        } 
    }
    
    
    
    
    public class Product {
        private String item;
        private String price_of_item;
        
        Product(String item, String price_of_item) {
            this.item = item;
            this.price_of_item = price_of_item;
        }
        public String getItem() {
            return item;
        }
        public void setItem(String item) {
            this.item = item;
        }
        public String getPrice() {
            return price_of_item;
        }
        public void setPrice(String price) {
            this.price_of_item = price;
        }
    } 
    
    private void itemComboBox() {
       
         ArrayList<String> comboItem = new ArrayList<>();
//        String temp = "";
        String query = "SELECT `category` FROM `admin_data`";
        
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery(query);
            while (rs.next()) {
                
                // Executing Count Query to count the number of category
//                temp = rs.getString("category");
//                String cnt_query = "SELECT COUNT(category) AS total FROM item_avail WHERE category = '" + 
//                temp + "'";
//                Statement p = DBConnect.DBConnect.getConnection().createStatement();
//                ResultSet rs1 = p.executeQuery(cnt_query);
//                rs1.next();
//                System.out.println(rs1.getInt("total"));
//                System.out.println(cnt_query);
                if (!comboItem.contains(rs.getString("category"))) {
                    comboItem.add(rs.getString("category"));
                    jComboBox_category.addItem(rs.getString("category"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> getData(String category) {
        ArrayList<Product> myList = new ArrayList<>();
        String query = "SELECT `category`, `item_menu`, `price` FROM `admin_data`";
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery();
            
            Product newP;
            while (rs.next()) {
                if (rs.getString("category").equals(category)) {
                    newP = new Product(rs.getString("item_menu"), rs.getString("price"));
                    myList.add(newP);
                }                   
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    } 
    
    //------------------------------------------------------
    // setting function and class for printing invoice
    // -----------------------------------------------------
    
    public PageFormat getPageFormat(PrinterJob pj) {
    
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();    

        double bodyHeight = baseHeight;  
        double headerHeight = 15.0;                  
        double footerHeight = 15.0;        
        double width = cm_to_pp(8); 
        double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
        paper.setSize(width, height);
        paper.setImageableArea(0,12,width,height - cm_to_pp(1));  
        pf.setOrientation(PageFormat.PORTRAIT);  
        pf.setPaper(paper);    

        return pf;
    }
   
    
    
    protected static double cm_to_pp(double cm) {            
	return toPPI(cm * 0.393600787);            
    }
 
    protected static double toPPI(double inch) {            
	return inch * 72d;            
    }
    
    public class BillPrintable implements Printable {
    

    @Override
    public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
    throws PrinterException {    
      
        int r = item_selected.size();
        int result = NO_SUCH_PAGE;    
        
        if (pageIndex == 0) {                    

        Graphics2D g2d = (Graphics2D) graphics;                                                 
        g2d.translate((int)pageFormat.getImageableX(),(int)pageFormat.getImageableY()); 
        
        try {
            int y = 20;
            int yShift = 10;
            int headerRectHeight=15;
            
            
            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawString(" Customer Copy                       ",12,y); y+=yShift;
            g2d.drawString("-------------------------------------",12,y); y+=yShift;
            g2d.drawString("               RestX                 ",12,y); y+=yShift;
            g2d.drawString("       Kalyani, West Bengal          ",12,y); y+=yShift;
            g2d.drawString("       88888888, xyz@ex.com          ",12,y); y+=yShift;
            g2d.drawString("-------------------------------------",12,y); y+=headerRectHeight;

            g2d.drawString(" Item Name                  Price    ",10,y); y+=yShift;
            g2d.drawString("-------------------------------------",10,y); y+=headerRectHeight;
     
            for(int s=0; s<r; s++) {
            g2d.drawString(" " + item_selected.get(s)+"                            ", 10, y); y+=yShift;
            g2d.drawString("   QTY. " + qty_selected.get(s), 10, y); g2d.drawString(net_amt.get(s),160,y); y+=yShift;
            }
          
            g2d.drawString("-------------------------------------",10,y); y+=yShift;
            g2d.drawString(" Total amount:               " + jTextField_total.getText() + "   ", 10, y); y+=yShift;
            y+=yShift;
            g2d.drawString(" Date:  " + dateTime(), 10, y); y+=yShift;
            g2d.drawString("-------------------------------------",10,y); y+=yShift;

            g2d.drawString("*************************************",10,y); y+=yShift;
            g2d.drawString("      THANK YOU, COME AGAIN          ",10,y); y+=yShift;
            g2d.drawString("*************************************",10,y); y+=yShift;
            g2d.drawString("        Shibayan | Sandeep            ",10,y); y+=yShift;
            g2d.drawString("                END                   ",10,y); y+=yShift;
            y += yShift;
            y += yShift;
            y += yShift;
            y += yShift;
            g2d.drawString(" Kitchen Copy                       ",12,y); y+=yShift;
            g2d.drawString("-------------------------------------",12,y); y+=yShift;
            g2d.drawString("               RestX                 ",12,y); y+=yShift;
            g2d.drawString("       Kalyani, West Bengal          ",12,y); y+=yShift;
            g2d.drawString("       88888888, xyz@ex.com          ",12,y); y+=yShift;
            g2d.drawString("-------------------------------------",12,y); y+=headerRectHeight;

            g2d.drawString(" Item Name                    QTY.   ",10,y); y+=yShift;
            g2d.drawString("-------------------------------------",10,y); y+=headerRectHeight;
            for(int s=0; s<r; s++) {
                g2d.drawString(" " + item_selected.get(s), 10, y); g2d.drawString(qty_selected.get(s),175,y); y+=yShift;
            }
            g2d.drawString("-------------------------------------",10,y); y+=yShift;
           
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        result = PAGE_EXISTS;
        }    
        return result;    
    }
}
    
    
    
    public class BillData {
        String item;
        String qty;
        String price;
        
        public BillData(String item, String qty, String price) {
            this.item = item;
            this.qty = qty;
            this.price = price;
        }
        
        void setItem(String item) {
            this.item = item;
        }
        String getItem() {
            return item;
        }
        void setQty(String qty) {
            this.qty = qty;
        }
        String getQty() {
            return qty;
        }
        void setPrice(String price) {
            this.price = price;
        }
        String getPrice() {
            return price;
        }
    }
    
    public ArrayList<BillData> getAllBilling() {
        ArrayList<BillData> new_bill_ar = new ArrayList<>();
        BillData new_bill;
        int total_row = jTable_selected_item.getRowCount();
        if (total_row>0) {
            for (int i = 0; i < total_row; i++) {
                new_bill = new BillData(jTable_selected_item.getValueAt(i, 0).toString(),
                jTable_selected_item.getValueAt(i, 1).toString(),
                jTable_selected_item.getValueAt(i, 2).toString());
                new_bill_ar.add(new_bill);
            }
        }
        return new_bill_ar;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_restxname = new javax.swing.JLabel();
        jComboBox_category = new javax.swing.JComboBox<>();
        jTextField_qs = new javax.swing.JTextField();
        jLabel_minimize = new javax.swing.JLabel();
        jLabel_close = new javax.swing.JLabel();
        jLabel_avail_items = new javax.swing.JLabel();
        jScrollPane_avl_itm = new javax.swing.JScrollPane();
        jTable_avl_itm = new javax.swing.JTable();
        jLabel_border = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane_menu = new javax.swing.JScrollPane();
        jTable_menu = new javax.swing.JTable();
        jLabel_Quantity = new javax.swing.JLabel();
        jLabel_disc = new javax.swing.JLabel();
        jTextField_disc = new javax.swing.JTextField();
        jTextField_qty = new javax.swing.JTextField();
        jScrollPane_selected_item = new javax.swing.JScrollPane();
        jTable_selected_item = new javax.swing.JTable();
        jLabel_add = new javax.swing.JLabel();
        jLabel_remove = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_total = new javax.swing.JTextField();
        jLabel_bill_gen = new javax.swing.JLabel();
        jLabel_go_back_to_main = new javax.swing.JLabel();
        jLabel_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jLabel_restxname.setBackground(new java.awt.Color(153, 255, 153));
        jLabel_restxname.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel_restxname.setForeground(new java.awt.Color(204, 0, 102));
        jLabel_restxname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_restxname.setText("Welcome to RestX");
        jLabel_restxname.setName(""); // NOI18N
        getContentPane().add(jLabel_restxname);
        jLabel_restxname.setBounds(60, 0, 890, 70);
        jLabel_restxname.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));

        jComboBox_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Category" }));
        jComboBox_category.setSelectedItem(jComboBox_category);
        jComboBox_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_categoryActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_category);
        jComboBox_category.setBounds(330, 150, 280, 30);

        jTextField_qs.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextField_qs.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_qs.setText("Quick Search by Item Name");
        jTextField_qs.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jTextField_qs.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jTextField_qsComponentAdded(evt);
            }
        });
        jTextField_qs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_qsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_qsFocusLost(evt);
            }
        });
        jTextField_qs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_qsActionPerformed(evt);
            }
        });
        jTextField_qs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_qsKeyReleased(evt);
            }
        });
        getContentPane().add(jTextField_qs);
        jTextField_qs.setBounds(20, 150, 290, 30);

        jLabel_minimize.setBackground(new java.awt.Color(204, 204, 255));
        jLabel_minimize.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel_minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_minimize.setText("-");
        jLabel_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_minimize);
        jLabel_minimize.setBounds(930, 10, 40, 30);

        jLabel_close.setBackground(new java.awt.Color(153, 0, 0));
        jLabel_close.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close.setText("X");
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_closeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_close);
        jLabel_close.setBounds(970, 10, 40, 30);

        jLabel_avail_items.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabel_avail_items.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_avail_items.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_avail_items.setText("Available Items");
        getContentPane().add(jLabel_avail_items);
        jLabel_avail_items.setBounds(50, 110, 200, 30);

        jTable_avl_itm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Menu", "QTY."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane_avl_itm.setViewportView(jTable_avl_itm);
        if (jTable_avl_itm.getColumnModel().getColumnCount() > 0) {
            jTable_avl_itm.getColumnModel().getColumn(1).setResizable(false);
            jTable_avl_itm.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane_avl_itm);
        jScrollPane_avl_itm.setBounds(20, 190, 290, 290);

        jLabel_border.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(153, 255, 255)));
        getContentPane().add(jLabel_border);
        jLabel_border.setBounds(320, 120, 10, 380);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create Bill");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(600, 110, 120, 30);

        jTable_menu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Price"
            }
        ));
        jTable_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_menuMouseClicked(evt);
            }
        });
        jScrollPane_menu.setViewportView(jTable_menu);

        getContentPane().add(jScrollPane_menu);
        jScrollPane_menu.setBounds(330, 190, 280, 200);

        jLabel_Quantity.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel_Quantity.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_Quantity.setText("Quantity");
        getContentPane().add(jLabel_Quantity);
        jLabel_Quantity.setBounds(450, 440, 70, 30);

        jLabel_disc.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel_disc.setForeground(new java.awt.Color(51, 51, 51));
        jLabel_disc.setText("Discount(%)");
        getContentPane().add(jLabel_disc);
        jLabel_disc.setBounds(430, 400, 100, 30);

        jTextField_disc.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTextField_disc.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextField_disc.setEnabled(false);
        jTextField_disc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_discActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_disc);
        jTextField_disc.setBounds(530, 400, 80, 30);

        jTextField_qty.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTextField_qty.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        getContentPane().add(jTextField_qty);
        jTextField_qty.setBounds(530, 440, 80, 30);

        jTable_selected_item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Net QTY.", "Net Amount"
            }
        ));
        jTable_selected_item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_selected_itemMouseClicked(evt);
            }
        });
        jScrollPane_selected_item.setViewportView(jTable_selected_item);

        getContentPane().add(jScrollPane_selected_item);
        jScrollPane_selected_item.setBounds(730, 150, 270, 240);

        jLabel_add.setBackground(new java.awt.Color(0, 153, 255));
        jLabel_add.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel_add.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_add.setText("<< ADD");
        jLabel_add.setOpaque(true);
        jLabel_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_addMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_addMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_addMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_add);
        jLabel_add.setBounds(620, 200, 100, 40);

        jLabel_remove.setBackground(new java.awt.Color(255, 102, 102));
        jLabel_remove.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel_remove.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_remove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_remove.setText("REMOVE >>");
        jLabel_remove.setOpaque(true);
        jLabel_remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_removeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_removeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_removeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_removeMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_remove);
        jLabel_remove.setBounds(620, 250, 100, 40);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(840, 410, 40, 20);

        jTextField_total.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTextField_total.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextField_total.setEnabled(false);
        getContentPane().add(jTextField_total);
        jTextField_total.setBounds(890, 400, 110, 30);

        jLabel_bill_gen.setBackground(new java.awt.Color(0, 204, 204));
        jLabel_bill_gen.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel_bill_gen.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_bill_gen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_bill_gen.setText("GENERATE BILL");
        jLabel_bill_gen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_bill_gen.setOpaque(true);
        jLabel_bill_gen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_bill_genMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_bill_genMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_bill_genMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_bill_genMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_bill_genMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_bill_gen);
        jLabel_bill_gen.setBounds(620, 300, 100, 60);

        jLabel_go_back_to_main.setBackground(new java.awt.Color(204, 153, 0));
        jLabel_go_back_to_main.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        jLabel_go_back_to_main.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_go_back_to_main.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_go_back_to_main.setText("Go to Main Menu");
        jLabel_go_back_to_main.setOpaque(true);
        jLabel_go_back_to_main.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel_go_back_to_mainMouseReleased(evt);
            }
        });
        getContentPane().add(jLabel_go_back_to_main);
        jLabel_go_back_to_main.setBounds(850, 450, 150, 40);

        jLabel_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/blue_bg.png"))); // NOI18N
        jLabel_bg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel_bgMouseDragged(evt);
            }
        });
        jLabel_bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel_bgMousePressed(evt);
            }
        });
        getContentPane().add(jLabel_bg);
        jLabel_bg.setBounds(-10, -10, 1030, 520);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_bgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bgMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_jLabel_bgMousePressed

    private void jLabel_bgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bgMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - pX, 
                this.getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_jLabel_bgMouseDragged

    private void jTextField_qsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_qsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_qsActionPerformed

    private void jTextField_qsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_qsFocusGained
        if (jTextField_qs.getText().toLowerCase().equals("quick search by item name")) {
            jTextField_qs.setText("");
            jTextField_qs.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField_qsFocusGained

    private void jTextField_qsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_qsFocusLost
        if (jTextField_qs.getText().equals("")) {
            jTextField_qs.setText("Quick Search by Item Name");
            jTextField_qs.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jTextField_qsFocusLost

    private void jTextField_qsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_qsKeyReleased
        String query = "SELECT `category` AS Category, `menu_item` AS Items, `quantity` AS Quantity FROM `item_avail` WHERE `menu_item` LIKE '%" + 
                jTextField_qs.getText() + "%'";
        try {
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery();
            jTable_avl_itm.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTextField_qsKeyReleased

    private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseClicked
        this.setState(FirstWelcomeScrn.ICONIFIED);
    }//GEN-LAST:event_jLabel_minimizeMouseClicked

    private void jLabel_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseEntered
        jLabel_minimize.setBackground(Color.gray);
        jLabel_minimize.setOpaque(true);
    }//GEN-LAST:event_jLabel_minimizeMouseEntered

    private void jLabel_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseExited
        jLabel_minimize.setBackground(new Color(0,0,0,0));
        jLabel_minimize.setOpaque(false);
    }//GEN-LAST:event_jLabel_minimizeMouseExited

    private void jLabel_minimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMousePressed
        jLabel_minimize.setBackground(Color.darkGray);
    }//GEN-LAST:event_jLabel_minimizeMousePressed

    private void jLabel_minimizeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseReleased
        jLabel_minimize.setBackground(new Color(0,0,0,0));
    }//GEN-LAST:event_jLabel_minimizeMouseReleased

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jLabel_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseEntered
        jLabel_close.setBackground(Color.red);
        jLabel_close.setOpaque(true);
    }//GEN-LAST:event_jLabel_closeMouseEntered

    private void jLabel_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseExited
        jLabel_close.setBackground(new Color(0,0,0,0));
        jLabel_close.setOpaque(false);
    }//GEN-LAST:event_jLabel_closeMouseExited

    private void jLabel_closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMousePressed

        jLabel_close.setBackground(new Color(153,0,0));
    }//GEN-LAST:event_jLabel_closeMousePressed

    private void jLabel_closeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseReleased
        jLabel_close.setBorder(BorderFactory.createEmptyBorder());
        jLabel_close.setBackground(new Color(0,0,0,0));
    }//GEN-LAST:event_jLabel_closeMouseReleased

    private void jTextField_qsComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTextField_qsComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_qsComponentAdded

    private void jTextField_discActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_discActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_discActionPerformed

    private void jComboBox_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_categoryActionPerformed
        CustomerPortal new_cs= new CustomerPortal();
        ArrayList<Product> myList;
        myList = new_cs.getData(jComboBox_category.getSelectedItem().toString());
        menuTable = new DefaultTableModel();
        menuTable.setColumnIdentifiers(new Object[]{"Item","Price"});
        Object[] row = new Object[2];
//        System.out.println(myList.size());
        for (int i = 0; i < myList.size(); i++) {
            row[0] = myList.get(i).getItem();
            row[1] = myList.get(i).getPrice();
            menuTable.addRow(row);
        }
        jTable_menu.setModel(menuTable);
    }//GEN-LAST:event_jComboBox_categoryActionPerformed

    private void jTable_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_menuMouseClicked
        try {
            int i = jTable_menu.getSelectedRow();
            TableModel model = jTable_menu.getModel();
            String item = (model.getValueAt(i, 0).toString());
            
            String query = "SELECT `item_menu`, `discount` FROM `admin_data`";
            p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
            rs = p_st.executeQuery();
            
            while (rs.next()) {
                if (rs.getString("item_menu").equals(item)) {
                    jTextField_disc.setText(Float.toString(rs.getFloat("discount")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTable_menuMouseClicked

    private void jLabel_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_addMouseClicked
        String text_qty = jTextField_qty.getText();
        String discount = jTextField_disc.getText();
        // Row count of jTable_selected_item
        
        
        
        if (jTable_selected_item.getRowCount() == 0) {
        billTable = new DefaultTableModel();
        billTable.setColumnIdentifiers(new Object[]{"Item","Net QTY.", "Net Amount"});
        }
        Object[] row = new Object[3];
        try {
        int i = jTable_menu.getSelectedRow();
        // Get quantity value from jTextField_qty
        int qty;
        qty = Integer.parseInt(text_qty);
        float total_amount_before_disc, total_amount_after_disc, price_per_item, disc,
                total_amount_of_all_items = 0;
        
        
        int avail_quantity = 0, no_of_rows_avail, target_row, flag = 0;
            no_of_rows_avail = jTable_avl_itm.getRowCount();
            // Searching availability table to match the item
            for (target_row = 0; target_row < no_of_rows_avail; target_row++) {
                if (jTable_avl_itm.getValueAt(target_row, 1).toString().equals(jTable_menu.getValueAt(i, 0).toString())) {
                    flag = 1;
                    break;
                }
            }
            if (flag != 0) {
        avail_quantity = Integer.parseInt(jTable_avl_itm.getValueAt(target_row, 2).toString());
            }
        
        if (text_qty.equals("") || !text_qty.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Please enter valid quantity!", "Wrong Quantity", 1);
        } else if (avail_quantity < qty || flag == 0) {
            JOptionPane.showMessageDialog(null, "Quantity not available!", "Quantity!",1);
        } else {
            disc = Float.parseFloat(discount);
            // 1st row is item name
            row[0] = jTable_menu.getValueAt(i, 0);
            // 2nd row is net quantity
            row[1] = qty;
            // 3rd row is total amount calculation
            price_per_item = Float.parseFloat(jTable_menu.getValueAt(i, 1).toString());
            total_amount_before_disc = (price_per_item*qty);
            total_amount_after_disc = total_amount_before_disc - ((total_amount_before_disc)*disc)/100;
            row[2] = total_amount_after_disc;
            
            
            // Updating the quantity of available items
            avail_quantity = avail_quantity - qty;
            jTable_avl_itm.setValueAt(avail_quantity, target_row, 2);
            
            
//            System.out.println(total_amount_of_all_items);
            billTable.addRow(row);
        }
        jTable_selected_item.setModel(billTable);
        jTextField_qty.setText("");
        
        int row_number = jTable_selected_item.getRowCount();
        for (int j = 0; j < row_number; j++) {
            total_amount_of_all_items += Float.parseFloat(jTable_selected_item.getValueAt(j, 2).toString());
        }
        jTextField_total.setText(Float.toString(total_amount_of_all_items));
        } catch(ArrayIndexOutOfBoundsException e) {
            System.err.println("ArrayIndexOutOfBoundsException: " + e.toString());
            JOptionPane.showMessageDialog(null, "Select an item.", "Selection error!", 1);
        } catch(NumberFormatException c) {
            System.err.println("NumberFormatException: " + c.toString());
            JOptionPane.showMessageDialog(null, "Enter Valid Quantity.", "Invalid Quantity!", 1);
        }
    }//GEN-LAST:event_jLabel_addMouseClicked

    private void jLabel_removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMouseClicked

        int qty;
        
        try {
        int i = jTable_selected_item.getSelectedRow();
        String text_qty = jTable_selected_item.getValueAt(i, 1).toString();
        qty = Integer.parseInt(text_qty);
        
        int avail_quantity, no_of_rows_avail, target_row;
            no_of_rows_avail = jTable_avl_itm.getRowCount();
            // Searching availability table to match the item
            for (target_row = 0; target_row < no_of_rows_avail; target_row++) {
                if (jTable_avl_itm.getValueAt(target_row, 1).toString().equals(jTable_selected_item.getValueAt(i, 0).toString())) {
                    break;
                }
            }
        avail_quantity = Integer.parseInt(jTable_avl_itm.getValueAt(target_row, 2).toString());
            if (i>-1) {
                avail_quantity = avail_quantity + qty;
                jTable_avl_itm.setValueAt(avail_quantity, target_row, 2);

                ((DefaultTableModel)jTable_selected_item.getModel()).removeRow(i);

            } else {
                JOptionPane.showMessageDialog(null,"Please select a row!" ,"Invalid Selection!",1);
            }
            int row_number = jTable_selected_item.getRowCount();
            float total_amount_of_all_items = 0;
            for (int j = 0; j < row_number; j++) {
                total_amount_of_all_items += Float.parseFloat(jTable_selected_item.getValueAt(j, 2).toString());
            }
            jTextField_total.setText(Float.toString(total_amount_of_all_items));
        } catch(ArrayIndexOutOfBoundsException e) {
            System.err.println("ArrayIndexOutOfBoundsException: " + e.toString());
            JOptionPane.showMessageDialog(null, "Select an item.", "Selection error!", 1);
        } catch(NumberFormatException c) {
            System.err.println("NumberFormatException: " + c.toString());
            JOptionPane.showMessageDialog(null, "Select an item.", "Selection error!", 1);
        }
    }//GEN-LAST:event_jLabel_removeMouseClicked

    private void jLabel_go_back_to_mainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMouseClicked
        new RestXInterface.FirstWelcomeScrn().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel_go_back_to_mainMouseClicked

    private void jTable_selected_itemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_selected_itemMouseClicked
        
    }//GEN-LAST:event_jTable_selected_itemMouseClicked

    private void jLabel_bill_genMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bill_genMouseClicked
        try {
            // --------------------------------------------------------
            // Getting all datas from jTable_selected_item to ArrayList
            // --------------------------------------------------------
            int no_rows_selected_table = jTable_selected_item.getRowCount();
            String item_name, qty, total_amt;
            int target_row = 0;
            
            item_selected = new ArrayList<>();
            qty_selected = new ArrayList<>();
            net_amt = new ArrayList<>();
            
            if (no_rows_selected_table > 0) {
                while (target_row != no_rows_selected_table) {                    
                    item_name = jTable_selected_item.getValueAt(target_row, 0).toString();
                    qty = jTable_selected_item.getValueAt(target_row, 1).toString();
                    total_amt = jTable_selected_item.getValueAt(target_row, 2).toString();
                    
                    // Copy Table items into arraylist
                    item_selected.add(item_name);
                    qty_selected.add(qty);
                    net_amt.add(total_amt);
                    
                    target_row += 1;
                }
                
                // ----------------------------------------------
                // Click to Print
                // ----------------------------------------------
                    
                baseHeight = Double.valueOf(item_selected.size());
                //JOptionPane.showMessageDialog(rootPane, bHeight);

                PrinterJob pj = PrinterJob.getPrinterJob();        
                pj.setPrintable(new BillPrintable(),getPageFormat(pj));
                boolean doPrint = pj.printDialog();
                if (doPrint) {
                    try {
                        pj.print();
                        
                        // Update avail database
                PreparedStatement upd_st;
                String target_menu = "";
                int update_qty = 0;
                String query = "SELECT `menu_item`, `quantity` FROM `item_avail`";
                String update = "UPDATE `item_avail` SET `quantity`= ? WHERE menu_item = ?";
                try {
                    // Connect to the database
                    p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
                    rs = p_st.executeQuery();
                    
                    
                        while (rs.next()) {
                            for (int j = 0; j < no_rows_selected_table; j++) {
                            target_menu = jTable_selected_item.getValueAt(j, 0).toString();
                            int avl_qty = rs.getInt("quantity");
                                if (target_menu.equals(rs.getString("menu_item"))) {
                                    
                                    update_qty = avl_qty - Integer.parseInt(jTable_selected_item.getValueAt(j, 1).toString());
                                    
                                    // Executing update in database
                                    upd_st = DBConnect.DBConnect.getConnection().prepareStatement(update);
                                    upd_st.setInt(1, update_qty);
                                    upd_st.setString(2, target_menu);
                                    upd_st.executeUpdate();
                                    System.out.println(target_menu);
//                                    upd_st.close();
                                    break;
                                }
                            System.out.println(j);
                        }
                    }
//                    p_st.close();
                    
                    // Adding billing datas to the databases
                    PreparedStatement p_st_bill;
                    ArrayList<BillData> new_bill = getAllBilling();
                    String all_item = "", all_qty = "", all_price = "";
                    all_item = new_bill.get(0).getItem();
                    all_qty = new_bill.get(0).getQty();
                    all_price = new_bill.get(0).getPrice();
                    if (no_rows_selected_table > 1) {
                        for (int j = 1; j < no_rows_selected_table; j++) {
                            // setting whole string to insert into database
                            all_item += "\n" + new_bill.get(j).getItem();
                            all_qty += "\n" + new_bill.get(j).getQty();
                            all_price += "\n" + new_bill.get(j).getPrice();
                        }
                    }
                    String query_bill = "INSERT INTO `billing_details` (`item`, `quantity`,"
                                    + "`price`, `date_time`, `total_price`) VALUES ('"
                                    + all_item + "', '"
                                    + all_qty + "', '" 
                                    + all_price + "', '"
                                    + dateTime() + "', '" 
                                    + jTextField_total.getText() + "')";
                    p_st_bill = DBConnect.DBConnect.getConnection().prepareStatement(query_bill);
                    p_st_bill.executeUpdate();
                    
                    
                    ((DefaultTableModel)jTable_selected_item.getModel()).setRowCount(0);
                    jTextField_total.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                        
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                } else {
//                JOptionPane.showMessageDialog(null, "No Selection!", "Selection Error!", 3);
                }
                
                
//                // Update avail database
//                PreparedStatement upd_st;
//                String target_menu = "";
//                int update_qty = 0;
//                String query = "SELECT `menu_item`, `quantity` FROM `item_avail`";
//                String update = "UPDATE `item_avail` SET `quantity`= ? WHERE menu_item = ?";
//                try {
//                    // Connect to the database
//                    p_st = DBConnect.DBConnect.getConnection().prepareStatement(query);
//                    rs = p_st.executeQuery();
//
//                    int i = jTable_selected_item.getRowCount();
//                    
//                    
//                        while (rs.next()) {
//                            for (int j = 0; j < i; j++) {
//                            target_menu = jTable_selected_item.getValueAt(j, 0).toString();
//                            int avl_qty = rs.getInt("quantity");
//                                if (target_menu.equals(rs.getString("menu_item"))) {
//                                    
//                                    update_qty = avl_qty - Integer.parseInt(jTable_selected_item.getValueAt(j, 1).toString());
//                                    
//                                    // Executing update in database
//                                    upd_st = DBConnect.DBConnect.getConnection().prepareStatement(update);
//                                    upd_st.setInt(1, update_qty);
//                                    upd_st.setString(2, target_menu);
//                                    upd_st.executeUpdate();
//                                    System.out.println(target_menu);
////                                    upd_st.close();
//                                    break;
//                                }
//                            System.out.println(i);
//                        }
//                    }
////                    p_st.close();
//                    
//                    ((DefaultTableModel)jTable_selected_item.getModel()).setRowCount(0);
//                    jTextField_total.setText("");
//                } catch (SQLException ex) {
//                    Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, ex);
//                }
                } else {
                JOptionPane.showMessageDialog(null, "No items in bill.", "Billing error!", 2);
            }
            
        } catch(ArrayIndexOutOfBoundsException e) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "No selectionarr!", "Selection error!", 3);
        } catch(IndexOutOfBoundsException c) {
            Logger.getLogger(CustomerPortal.class.getName()).log(Level.SEVERE, null, c);
            JOptionPane.showMessageDialog(null, "No selectionind!", "Selection error!", 3);
        }
    }//GEN-LAST:event_jLabel_bill_genMouseClicked

    private void jLabel_addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_addMouseEntered
        jLabel_add.setBackground(new Color(0,102,255));
    }//GEN-LAST:event_jLabel_addMouseEntered

    private void jLabel_addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_addMouseExited
        jLabel_add.setBackground(new Color(0,153,255));
    }//GEN-LAST:event_jLabel_addMouseExited

    private void jLabel_addMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_addMousePressed
//        jLabel_add.setBorder(BorderFactory.createEtchedBorder());
        jLabel_add.setBackground(new Color(0,51,204));
    }//GEN-LAST:event_jLabel_addMousePressed

    private void jLabel_addMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_addMouseReleased
        jLabel_add.setBorder(BorderFactory.createEmptyBorder());
        jLabel_add.setBackground(new Color(0,102,255));
    }//GEN-LAST:event_jLabel_addMouseReleased

    private void jLabel_removeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMouseEntered
        jLabel_remove.setBackground(new Color(255,51,51));
    }//GEN-LAST:event_jLabel_removeMouseEntered

    private void jLabel_removeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMouseExited
        jLabel_remove.setBackground(new Color(255,102,102));
    }//GEN-LAST:event_jLabel_removeMouseExited

    private void jLabel_removeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMousePressed
//        jLabel_remove.setBorder(BorderFactory.createEtchedBorder());
        jLabel_remove.setBackground(new Color(204,0,51));
    }//GEN-LAST:event_jLabel_removeMousePressed

    private void jLabel_removeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removeMouseReleased
        jLabel_remove.setBorder(BorderFactory.createEmptyBorder());
        jLabel_remove.setBackground(new Color(255,102,102));
    }//GEN-LAST:event_jLabel_removeMouseReleased

    private void jLabel_bill_genMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bill_genMouseEntered
        jLabel_bill_gen.setBackground(new Color(0,153,153));
    }//GEN-LAST:event_jLabel_bill_genMouseEntered

    private void jLabel_bill_genMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bill_genMouseExited
        jLabel_bill_gen.setBackground(new Color(0,204,204));
    }//GEN-LAST:event_jLabel_bill_genMouseExited

    private void jLabel_bill_genMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bill_genMousePressed
        jLabel_bill_gen.setBackground(new Color(0,102,102));
    }//GEN-LAST:event_jLabel_bill_genMousePressed

    private void jLabel_bill_genMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_bill_genMouseReleased
        jLabel_bill_gen.setBackground(new Color(0,204,204));
    }//GEN-LAST:event_jLabel_bill_genMouseReleased

    private void jLabel_go_back_to_mainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMouseEntered
        jLabel_go_back_to_main.setBackground(new Color(153,102,0));
    }//GEN-LAST:event_jLabel_go_back_to_mainMouseEntered

    private void jLabel_go_back_to_mainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMouseExited
        jLabel_go_back_to_main.setBackground(new Color(204,153,0));
    }//GEN-LAST:event_jLabel_go_back_to_mainMouseExited

    private void jLabel_go_back_to_mainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMousePressed
        jLabel_go_back_to_main.setBackground(new Color(102,51,0));
    }//GEN-LAST:event_jLabel_go_back_to_mainMousePressed

    private void jLabel_go_back_to_mainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_go_back_to_mainMouseReleased
        jLabel_go_back_to_main.setBackground(new Color(204,153,0));
    }//GEN-LAST:event_jLabel_go_back_to_mainMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CustomerPortal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox_category;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_Quantity;
    private javax.swing.JLabel jLabel_add;
    private javax.swing.JLabel jLabel_avail_items;
    private javax.swing.JLabel jLabel_bg;
    private javax.swing.JLabel jLabel_bill_gen;
    private javax.swing.JLabel jLabel_border;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_disc;
    private javax.swing.JLabel jLabel_go_back_to_main;
    private javax.swing.JLabel jLabel_minimize;
    private javax.swing.JLabel jLabel_remove;
    private javax.swing.JLabel jLabel_restxname;
    private javax.swing.JScrollPane jScrollPane_avl_itm;
    private javax.swing.JScrollPane jScrollPane_menu;
    private javax.swing.JScrollPane jScrollPane_selected_item;
    private javax.swing.JTable jTable_avl_itm;
    private javax.swing.JTable jTable_menu;
    private javax.swing.JTable jTable_selected_item;
    private javax.swing.JTextField jTextField_disc;
    private javax.swing.JTextField jTextField_qs;
    private javax.swing.JTextField jTextField_qty;
    private javax.swing.JTextField jTextField_total;
    // End of variables declaration//GEN-END:variables
}
