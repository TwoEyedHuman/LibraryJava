import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Library {

	static Book specBook = new Book("BOOK_TITLE","BOOK_AUTHOR",0);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   JFrame frame = new JFrame("My First GUI");
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setSize(600,400);
	   JPanel infoPanel = new JPanel();
	   JLabel bookInfo = new JLabel(specBook.toHTML());
	   JPanel panel = new JPanel();
	   JLabel label = new JLabel("Book");
	   JTextField tf = new JTextField(32);
	   JButton send = new JButton("Request");
	   panel.add(label);
	   panel.add(tf);
	   panel.add(send);
	   infoPanel.add(bookInfo);
	   frame.getContentPane().add(BorderLayout.NORTH, infoPanel);
	   frame.getContentPane().add(BorderLayout.SOUTH, panel);

	   send.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent ae) {
			   specBook = GetBook(tf.getText());
			   bookInfo.setText(specBook.toHTML());
			   infoPanel.revalidate();
			   infoPanel.repaint();
		   }
	   });
	   
	   frame.setVisible(true);
	}
	
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost/drexel","blocke","");
	}

	public static void ViewBooks() {
		String SQL = "select title from books";
		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			
			while (rs.next()) {
				System.out.print(String.format("Title: %s\n", rs.getString("title")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void GetBookInfo(String title) {
		String SQL = "select title, author from books where title = ?";
		try {
			Connection conn = connect();

			PreparedStatement stmt = conn.prepareStatement(SQL);
			stmt.setString(1, title);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.print(String.format("Title: %s\nAuthor: %s\n", rs.getString("title"), rs.getString("author")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Book GetBook(String title) {
		String SQL = "select title, author, pages from books where title = ?";
		try {
			Connection conn = connect();

			PreparedStatement stmt = conn.prepareStatement(SQL);
			stmt.setString(1, title);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Book returnBook = new Book(rs.getString("title"), rs.getString("Author"), rs.getInt("pages"));
				return returnBook;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Book NewBook(String title, String author, int pages) {
		String SQL = "insert into books (title, author, pages) values (?,?,?)";
		Book returnBook = null;
		
		try {
			Connection conn = connect();
			PreparedStatement stmt = conn.prepareStatement(SQL);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setInt(3,pages);
			stmt.execute();
			conn.close();
			
			returnBook = new Book(title, author, pages);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return returnBook;
	}
}
