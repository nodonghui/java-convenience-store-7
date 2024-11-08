package store.input;

import store.product.BuyItem;
import store.product.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import camp.nextstep.edu.missionutils.Console;

public class PurchaseInput implements  Input{

    private final String inputValue;
    private final HashMap<String, BuyItem> buyItems;
    private HashMap<String, Item> convenienceItems;

    PurchaseInput(HashMap<String, Item> ConvenienceItems) {
        this.buyItems=new HashMap<>();
        this.inputValue=getUserInput();
        this.convenienceItems=ConvenienceItems;

        addBuyItem(inputValue);

    }
///////////////////////////////////////////////////////////////////////////////////////////////
    void addBuyItem(String inputValue){
        String[] productEntries=inputValue.split(",");
        try {
            for (String entry : productEntries) {
                BuyItem buyItem=convertEntry(entry);
                buyItems.put(buyItem.getName(),buyItem);
            }
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] 정수 변환에 실패했습니다.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
//////////////////////////////////////////////////////////////////////////////////
    }
    BuyItem convertEntry(String entry) {
        entry=SubStringEntry(entry);

        String[] parts = entry.split("-");
        validationSplit(parts);

        String name = parts[0].trim();
        int quantity = Integer.parseInt(parts[1].trim());
        validationNegativeQuantity(quantity);
        validationContainBuyitemName(name);

        return new BuyItem(name,quantity);
    }
///////////////////////////////////////////////////////////////////////////////////////////////
    String SubStringEntry(String entry) {
        entry = entry.trim();
        validationSubstring(entry);
        entry = entry.substring(1, entry.length() - 1);

        return entry;
    }
//-----------------------------------------------------------------------------------------
    void validationSubstring(String input) {
        if(!(input.startsWith("[") && input.endsWith("]"))) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식의 입력입니다(,).");
        }

    }

     void validationSplit(String[] input) {
        if(input.length !=2)  {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식의 입력입니다(-).");
        }
    }

    void validationNegativeQuantity(int input) {
        if(input<0) {
            throw new IllegalArgumentException("[ERROR] 수량에 음수를 입력할수없습니다.");
        }
    }

    void validationContainBuyitemName(String input) {
        boolean flag=false;
        if(!convenienceItems.containsKey(input)) {
            throw  new IllegalArgumentException("[ERROR] 편의점에 해당 상품이 존재하지 않습니다.");
        }

    }
////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String getInputValue() {
        return inputValue;
    }

    @Override
    public String getUserInput() {
        return  Console.readLine();
    }
}
