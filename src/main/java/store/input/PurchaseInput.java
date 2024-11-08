package store.input;

import camp.nextstep.edu.missionutils.Console;
import store.product.BuyItem;
import store.product.Item;

import java.util.ArrayList;
import java.util.List;

public class PurchaseInput implements  Input{

    private final String inputValue;
    private final List<BuyItem> BuyItems;
    private List<Item> ConvenienceItems;

    PurchaseInput(List<Item> ConvenienceItems) {
        this.BuyItems=new ArrayList<>();
        this.inputValue=getUserInput();
        this.ConvenienceItems=ConvenienceItems;

        addBuyItem(inputValue);

    }
///////////////////////////////////////////////////////////////////////////////////////////////
    void addBuyItem(String inputValue){
        String[] productEntries=inputValue.split(",");
        try {
            for (String entry : productEntries) {
                BuyItem buyItem=convertEntry(entry);
                BuyItems.add(buyItem);
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
        for(Item item:ConvenienceItems) {
            String name=item.getName();
            flag=checkItemName(input,name);
        }
        if(flag==false) {
            throw  new IllegalArgumentException("[ERROR] 편의점에 해당 상품이 존재하지 않습니다.");
        }
    }
////////////////////////////////////////////////////////////////////////////////////////
    boolean checkItemName(String input,String name) {
        if(name.equals(input)) {
            return true;
        }
        return false;
    }



    @Override
    public String getInputValue() {
        return inputValue;
    }

    @Override
    public String getUserInput() {
        return  Console.readLine();
    }
}
