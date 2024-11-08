package store.init;

import store.product.Item;
import store.promotion.Promotion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class ConvenienceItemsInit {

    private final HashMap<String,Item> convenienceItems;
    private final HashMap<String, Promotion> promotions;
    ConvenienceItemsInit() {
        convenienceItems=new HashMap<>();
        promotions=new HashMap<>();
        readItems();
        readPromotion();
    }

    void readItems() {
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rdh04\\java-convenience-store-7\\src\\main\\resources\\products.md"))) {
            String line;
            //csv파일 속성 데이터 스킵 둘째줄 부터 저장 할 데이터
            br.readLine();
            while((line = br.readLine()) !=null) {
                putItems(line);
            }
        } catch (IOException e) {
            System.err.println("[ERROR]파일 불러오기 에러");
        }
    }


    void readPromotion(){
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\rdh04\\java-convenience-store-7\\src\\main\\resources\\promotion.md"))) {
            String line;
            //csv파일 속성 데이터 스킵 둘째줄 부터 저장 할 데이터
            br.readLine();
            while((line = br.readLine()) !=null) {
                putPromotion(line);
            }
        } catch (IOException e) {
            System.err.println("[ERROR]파일 불러오기 에러");
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    void putItems(String line) {
        String[] splitLine=line.split(",");

        String name=splitLine[0];
        double price=Double.parseDouble(splitLine[1]);
        int quantity=Integer.parseInt(splitLine[2]);
        String promotion=splitLine[3];

        Item item=new Item(name,price,quantity,promotion);
        convenienceItems.put(name,item);
    }

    void putPromotion(String line) {
        String[] splitLine=line.split(",");

        String name = splitLine[0];
        int buy=Integer.parseInt(splitLine[1]);
        int get=Integer.parseInt(splitLine[2]);
        LocalDate startDate=LocalDate.parse(splitLine[3]);
        LocalDate endDate=LocalDate.parse(splitLine[4]);

        Promotion promotion=new Promotion(name,buy,get,startDate,endDate);
        promotions.put(name,promotion);
    }


    //get method

    HashMap<String,Item> getConvenienceItems() {
        return  convenienceItems;
    }

    HashMap<String,Promotion> getPromotions() {
        return  promotions;
    }
}
