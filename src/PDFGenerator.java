

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 */
public class PDFGenerator {
	
	private static final String DEST = "result.pdf"; // The path of the pdf file
	
    public static void main(String[] args) throws IOException {
    	User user = new User("Selphie", "selphie.dou@icloud.com");
    	user.setFoodName("eggs");
    	user.setFoodWeight(0.3);
    	new PDFGenerator().createPdf("results.pdf", user);
	}

	public void createPdf(String dest, User user) throws IOException {
		// Initialize PDF writer
		PdfWriter writer = new PdfWriter(dest);

		// Initialize PDF document
		PdfDocument pdf = new PdfDocument(writer);

		// Initialize document
		Document document = new Document(pdf);

		// Create a PdfFont
		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
	
		Calculator c = new Calculator();
		/**
		 * First Part: paragraph of description
		 */
		// Add a Paragraph
		document.add(new Paragraph("Dear " + user.getName() + ", ").setFont(font).setFontSize(20));
		// Create a List
		List list = new List().setSymbolIndent(12).setListSymbol("\u2022");
		// Add ListItem objects
		double annualGHG = c.getAnnualGHG(user.getFoodWeight());
		list.add(new ListItem("Your favourite food is " + user.getFoodName()))
				.add(new ListItem("By eating " + user.getFoodWeight() + "(kg) everyday for 1 year, you'll have " + annualGHG + " emmissions."))
				.add(new ListItem("which is eqvilant to " + c.getCarKMEquivalent(annualGHG) + " passenger car kilometers."))
		        .add(new ListItem("which is eqvilant to " + c.getEquivalentBulbLightDays(annualGHG) + " LED bulb light days."))
		        .add(new ListItem("which is eqvilant to " + c.getEquivalentFlightKM(annualGHG) + " flight km"));
		// Add the list
		document.add(list);

		
		/**
		 * Second part: table of recommended food
		 */
		document.add(new Paragraph(""));
		document.add(
				new Paragraph("Here are some similiar foods with lower CO2 emissions you may consider.").setFont(font));
		//Create a table frame
		document.setMargins(20, 20, 20, 20);
		PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
		Table table = new Table(new float[] { 2, 2, 2 });
		table.setWidth(UnitValue.createPercentValue(100));
		//add Table Header
		table.addHeaderCell(new Cell().add("FoodName").setFont(bold).setBackgroundColor(Color.LIGHT_GRAY));
		table.addHeaderCell(new Cell().add("Greenhouse Gas Value").setFont(bold).setBackgroundColor(Color.LIGHT_GRAY));
		table.addHeaderCell(new Cell().add("Calories/kg").setFont(bold).setBackgroundColor(Color.LIGHT_GRAY));
		ArrayList<String> recommendfood = c.getSimilarFood(user.getFoodName());
		String food1 = recommendfood.get(0);
		String food2 = recommendfood.get(1);
		String food3 = recommendfood.get(2);
		//add food to Table
		table.addCell(new Cell().add(food1));
		table.addCell(new Cell().add(String.valueOf(c.getFoodGHGEmission(food1,1.0))));
		table.addCell(new Cell().add(String.valueOf(c.getFoodCalories(food1))));
		table.addCell(new Cell().add(food2));
		table.addCell(new Cell().add(String.valueOf(c.getFoodGHGEmission(food2,1.0))));
		table.addCell(new Cell().add(String.valueOf(c.getFoodCalories(food2))));
		table.addCell(new Cell().add(food3));
		table.addCell(new Cell().add(String.valueOf(c.getFoodGHGEmission(food3,1.0))));
		table.addCell(new Cell().add(String.valueOf(c.getFoodCalories(food3))));
		document.add(table);

		
		/**
		 * Third Part: table of recommended dishes with pictures
		 */
	    document.add(new Paragraph(""));
		document.add(new Paragraph("With the above foods, you can make these delicious dishes:").setFont(font));
		//create table
		Table table1 = new Table(new float[] { 2, 2, 2, 2 });
		table1.setWidth(UnitValue.createPercentValue(100));
		document.setMargins(20, 20, 20, 20);
		
		//create HeaderCell
		table1.addHeaderCell(new Cell().add("DishName").setFont(bold).setBackgroundColor(Color.LIGHT_GRAY));
		table1.addHeaderCell(new Cell().add("DishGroup").setFont(bold).setBackgroundColor(Color.LIGHT_GRAY));
		table1.addHeaderCell(new Cell().add("CO2 Emissions").setFont(bold).setBackgroundColor(Color.LIGHT_GRAY));
		table1.addHeaderCell(new Cell().add("Image").setFont(bold).setBackgroundColor(Color.LIGHT_GRAY));
		
		//create content
		ArrayList<String> a1 = c.getDishesContainFood(food1);
		ArrayList<String> a2 = c.getDishesContainFood(food2);
		ArrayList<String> a3 = c.getDishesContainFood(food3);
		table1.addCell(new Cell().add(a1.get(0)));
		table1.addCell(new Cell().add(c.getDishCategory(a1.get(0))));
		table1.addCell(new Cell().add(String.valueOf(c.getDishGHSEmission(a1.get(0)))));
		//Add pictures
		String f1 = "src/" + c.getDishPicPath(a1.get(0));
		Image pic1 = new Image(ImageDataFactory.create(f1));
		pic1.setAutoScaleHeight(true);
		table1.addCell(new Cell().add(pic1));
		
		if(!a2.equals(a1)) {
			table1.addCell(new Cell().add(a2.get(0)));
			table1.addCell(new Cell().add(c.getDishCategory(a2.get(0))));
			table1.addCell(new Cell().add(String.valueOf(c.getDishGHSEmission(a2.get(0)))));
			String f2 = "src/" +c.getDishPicPath(a2.get(0));
			Image pic2 = new Image(ImageDataFactory.create(f2));
			pic2.setAutoScaleHeight(true);
			table1.addCell(new Cell().add(pic2));
		}
		
		if(!a3.equals(a1) && !a3.equals(a2)) {
			table1.addCell(new Cell().add(a3.get(0)));
			table1.addCell(new Cell().add(c.getDishCategory(a3.get(0))));
			table1.addCell(new Cell().add(String.valueOf(c.getDishGHSEmission(a3.get(0)))));
			String f3 = "src/" +c.getDishPicPath(a3.get(0));
			Image pic3 = new Image(ImageDataFactory.create(f3));
			pic3.setAutoScaleHeight(true);
			table1.addCell(new Cell().add(pic3));
		}
		
		document.add(table1);

		/**
		 * Fourth part: closing picture
		 */
		//document.add(new Paragraph(""));
		String pic = "ecoearth.jpeg";
		Image earth = new Image(ImageDataFactory.create(pic));
		earth.setAutoScale(true).setRelativePosition(150, 0, 0, 0);
		document.add(earth);
		PdfFont soft = PdfFontFactory.createFont(FontConstants.TIMES_BOLDITALIC);
		Paragraph end = new Paragraph("We need every effort for the earth!");
		end.setRelativePosition(150, 0, 0, 0).setFontColor(Color.BLUE).setFont(soft).setFontSize(12);
		document.add(end);
		// Close document
		document.close();
	}
	
	
}
