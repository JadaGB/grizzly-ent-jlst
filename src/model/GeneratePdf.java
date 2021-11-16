package model;

import java.io.FileOutputStream;
import java.io.*;
import java.util.*;



import java.sql.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class GeneratePdf {  
	
        public static void main(String[] args) throws Exception{
                
                
//               	Class.forName ("oracle.jdbc.OracleDriver"); 
                Connection MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzly_ent","root", " ");
                Statement stmt = MyConn.createStatement();
                
               
                int id = 6;
				ResultSet rs = stmt.executeQuery("SELECT equipment.Name,customer.LastName,customer.FirstName,request.reqDate,request.quotation,request.confirmed,equipmenttype.type FROM ((request INNER JOIN customer ON request.cid ="+id +")"
                +" INNER JOIN equipmenttype ON request.eqID = equipmenttype.eqTypeID) INNER JOIN equipment ON equipment.TypeID = equipmenttype.eqTypeID");
               
                
                
                Document Invoice_Pdf = new Document();
                PdfWriter.getInstance(Invoice_Pdf, new FileOutputStream("Invoice.pdf"));
                Invoice_Pdf.open();            
                
                PdfPTable rt = new PdfPTable(6);
                //create a cell object
                PdfPCell table_cell;
               
                while (rs.next()) {                
                    String cusName = rs.getString("FirstName")+" "+rs.getString("LastName");
                    table_cell=new PdfPCell(new Phrase(cusName));
                    rt.addCell(table_cell);
                    String eqType=rs.getString("Type");
                    table_cell=new PdfPCell(new Phrase(eqType));
                    rt.addCell(table_cell);
                    String eqName=rs.getString("Name");
                    table_cell=new PdfPCell(new Phrase(eqName));
                    rt.addCell(table_cell);
                    String quote=rs.getString("quotation");
                    table_cell=new PdfPCell(new Phrase(quote));
                    rt.addCell(table_cell);
                    String reqDate=rs.getString("reqDate");
                    table_cell=new PdfPCell(new Phrase(reqDate));
                    rt.addCell(table_cell);
                    String status =rs.getString("confirmed");
                    table_cell=new PdfPCell(new Phrase(status));
                    rt.addCell(table_cell);
                }
                // adds table to doc
                Invoice_Pdf.add(rt);                       
                Invoice_Pdf.close();
                
               
                rs.close();
                stmt.close(); 
                MyConn.close();               
                
        }
}