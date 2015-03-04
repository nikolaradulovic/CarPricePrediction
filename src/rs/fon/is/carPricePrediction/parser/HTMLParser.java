package rs.fon.is.carPricePrediction.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Nikola on 3/4/2015.
 */
public class HTMLParser {

    private Document document;
    private String link;

    public HTMLParser(String link){
        this.link = link;
    }

    public int calculateNumberOfPages(){

        int numberOfPages = 0,numberOfCars = 0;
        try {
            document = Jsoup.connect(link).get();

            //Odredjeni deo stranice
            Elements pag = document.select("#search-results .pagination-counter-right #numOfAds");
            Element firstElement = pag.first();

            //Izracunavanje broja stranica, za kasniji prolazak for petljom kroz sve stranice
            numberOfCars = Integer.parseInt(firstElement.text());

            System.out.println("Ukupan broj pronadjenih automobila; "+ numberOfCars);
            numberOfPages = numberOfCars/25;

            System.out.println("Broj stranica pretrage: "+numberOfPages);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfPages;
    }

    public void generateData(int numberOfPages){

        String km="", date="", price="", horsePower="", fuel="", airCondition="", numOfDoors ="", gear="";

        for (int i=1; i<numberOfPages;i++){
            Document document;
            try{
                document = Jsoup.connect("http://www.polovniautomobili.com/putnicka-vozila/pretraga?page="+i+"&sort=renewDate_desc&brand=37&city_distance=0&showOldNew=all&without_price=1").get();

                //Prikupljanje podataka o predjenoj kilometrazi svih automobila
                Elements kilometres = document.select("#search-results .title-km");
                for (Element e: kilometres){
                    String carKm = e.text();
                    if(carKm != null && carKm.length() >= 2){
                        String kmFinal = carKm.replace(".","");
                        km+= kmFinal.substring(0, kmFinal.length() -2);
                    }
                }

                //Prikupljanje podataka o godini proizvodnje
                Elements year = document.select("#search-results .title-year");
                for (Element e:year){
                    String carYear = e.text();
                    if(carYear != null){
                        date+= carYear.substring(0, carYear.length() -4);
                    }
                }

                //Prikupljanje podataka o ceni automobila. Ukoliko se naidje na vrednost po dogovoru ili na upit,
                //ona se menja nekim nerealnim int brojem koji se kasnije izbacuje iz dataSeta.
                Elements prices = document.select("#search-results .title-price");
                for (Element e: prices){
                    String carPrice = e.text();
                    if (carPrice != null && carPrice.length() >=1){
                        if (carPrice.equals("Na upit") || carPrice.equals("Po dogovoru")){
                            price+= "1122334455 ";
                        }
                        else{
                            String priceFinal = carPrice.replace(".", "");
                            price+= priceFinal.substring(0,priceFinal.length() - 1);
                        }
                    }

                }

                // Svi ostali podaci koji su potrebni za DataSet nalaze u okviru jednog centralnog diva,
                //koga je potrebno rucno podaliti na manje delove.
                Elements info = document.select(".ad-infobox");
                for (Element e:info){

                    String value = e.text();
                    String[] parts = value.split(",");

                    //upisivanje jacina motora
                    String kw = (parts[3].split(" "))[1];

                    //Obradjujem izuzetak menjanjem u nerealni broj
                    if (kw.contains("Manuel") || kw.contains("A")){
                        kw = "112233445566";
                    }
                    horsePower+= kw.substring(0, kw.length() -2)+" ";

                    //upisivanje dizel/benzin, posto on vraca tekst ja sam ih zamenio brojevima 0 i 1 da bi mogle da se korsite u dataSetu
                    String[] engine_and_doors = (parts[2].split(" "));
                    String fuelType = engine_and_doors[engine_and_doors.length-1]+" ";
                    if (fuelType.contains("Dizel")){
                        fuel+="0 ";
                    }
                    else if (fuelType.contains("Benzin")){
                        fuel+="1 ";
                    }

                    //upisivanje klima i menjac
                    String[] carACandGear = (parts[4].split(" "));
                    String ac = carACandGear[carACandGear.length -2];
                    if (ac.contains("Manuelna"))
                        airCondition+="0 ";
                    else if(ac.contains("Automatska"))
                        airCondition+="1 ";
                    String gearType = carACandGear[1];
                    if(gearType.contains("Automatski"))
                        gear+="1 ";
                    else
                        gear+="0 ";

                    //upisivanje broj vrata
                    String[] doors = (parts[1].split(" "));
                    if (doors[1].equals("4/5"))
                        numOfDoors+= "1 ";
                    else
                        numOfDoors+="0 ";
                }

                BufferedWriter writer = null;
                try
                {
                    //upisivanje podataka u fajl
                    writer = new BufferedWriter( new FileWriter("data/unsortedData.txt"));
                    writer.write(km);
                    writer.newLine();
                    writer.write(price);
                    writer.newLine();
                    writer.write(date);
                    writer.newLine();
                    writer.write(horsePower);
                    writer.newLine();
                    writer.write(fuel);
                    writer.newLine();
                    writer.write(airCondition);
                    writer.newLine();
                    writer.write(numOfDoors);
                    writer.newLine();
                    writer.write(gear);
                    writer.close();

                }
                catch ( IOException e)
                {
                }
                finally
                {
                    try
                    {
                        if ( writer != null)
                            writer.close( );
                    }
                    catch ( IOException e)
                    {
                    }
                }
            }
             catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}
