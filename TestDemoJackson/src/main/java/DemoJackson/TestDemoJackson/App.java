package DemoJackson.TestDemoJackson;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException
    {
    	
    	//System.out.println( "Hello World!" );
        
        List<TitanicPassenger> TP = getPassengersFromJsonFile();
        
        graphPassengerAges(TP);
        
        graphPassengerClass(TP);
        
        graphPassengerSurvived(TP);
        
        graphPassengerSurvivedGender(TP);
        
    }
    
    //Read From Json
    public static List<TitanicPassenger> getPassengersFromJsonFile() throws JsonParseException, JsonMappingException, IOException {
    	List<TitanicPassenger> allPassengers= new ArrayList<TitanicPassenger> ();
    	
    	ObjectMapper objectMapper= new ObjectMapper();
    	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	
    	InputStream input = new FileInputStream ("F:\\youssef lap\\ITI\\7 Java\\Day6\\titanic_csv.json");
    	allPassengers= objectMapper.readValue(input, new TypeReference<List<TitanicPassenger>> () { });
    	
    	//Another way to read json file
    	//InputStream input = classloader.getResourceAsStream("test.csv");
    	//allPassengers = Arrays.asList(objectMapper.readValue(Paths.get("F:\\youssef lap\\ITI\\7 Java\\Day6\\titanic_csv.json").toFile(), TitanicPassenger[].class));
    	
    	return allPassengers;
    }
    
    public static void graphPassengerAges(List<TitanicPassenger> passengerList) {
    	//filter to get an array of passenger ages
    	List<Float> pAges= passengerList.stream().map (TitanicPassenger::getAge).limit (8).collect (Collectors.toList());
    	List<String> pNames= passengerList.stream().map (TitanicPassenger::getName).limit (8).collect (Collectors.toList());
    	
    	//Using XCartto graph the Ages 1.Create Chart
    	CategoryChart chart = new CategoryChartBuilder().width (1024).height (768).title ("Age Histogram").xAxisTitle("Names").yAxisTitle("Age").build ();
    	// 2.Customize Chart
    	chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
    	chart.getStyler().setHasAnnotations(true);chart.getStyler().setStacked(true);
    	// 3.Series
    	chart.addSeries("Passenger's Ages", pNames, pAges);
    	// 4.Show it
    	new SwingWrapper(chart).displayChart();
    	
    }
    
    public static void graphPassengerClass(List<TitanicPassenger> passengerList) {
    	//filter to get a map of passenger class and total number of passengers in each class
    	Map<String, Long> result =passengerList.stream().collect (Collectors.groupingBy(TitanicPassenger::getPclass, Collectors.counting() ) );
    	
    	// Create Chart
    	//PieChart chart = new PieChartBuilder().width (800).height (600).title (PieChartBuilder.class.getSimpleName()).build ();
    	PieChart chart = new PieChartBuilder().width (800).height (600).title ("Passenger Class").build ();

    	// Customize Chart
    	Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};
    	chart.getStyler().setSeriesColors(sliceColors);
    	// Series
    	chart.addSeries("First Class", result.get("1"));
    	chart.addSeries("Second Class", result.get("2"));
    	chart.addSeries("Third Class", result.get("3"));
    	// Show it
    	new SwingWrapper(chart).displayChart();
    }
    
    public static void graphPassengerSurvived(List<TitanicPassenger> passengerList) {
    	Map<String, Long> result =passengerList.stream().collect (Collectors.groupingBy(TitanicPassenger::getSurvived, Collectors.counting() ) );
    	
    	// Create Chart
    	PieChart chart = new PieChartBuilder().width (800).height (600).title ("Passenger Survived").build ();
    	// Customize Chart
    	Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120)};
    	chart.getStyler().setSeriesColors(sliceColors);
    	// Series
    	chart.addSeries("Survived", result.get("1"));
    	chart.addSeries("Not Survived", result.get("0"));
    	
    	// Show it
    	new SwingWrapper(chart).displayChart();
    }
    
    public static void graphPassengerSurvivedGender(List<TitanicPassenger> passengerList) {
    	Map<String, Long> result =passengerList.stream().filter(p -> p.getSurvived().equals("1")).collect (Collectors.groupingBy(TitanicPassenger::getSex, Collectors.counting() ) );
    	
    	// Create Chart
    	PieChart chart = new PieChartBuilder().width (800).height (600).title ("Passenger Survived Gender").build ();
    	// Customize Chart
    	Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120)};
    	chart.getStyler().setSeriesColors(sliceColors);
    	// Series
    	chart.addSeries("Male", result.get("male"));
    	chart.addSeries("Female", result.get("female"));
    	
    	// Show it
    	new SwingWrapper(chart).displayChart();
    }
}
