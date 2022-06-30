import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class ComparableOrderData extends OrderData implements Comparable<ComparableOrderData>{


    ComparableOrderData(@NotNull Type type,
                        @NotNull String currency,
                        @NotNull double amount){
        super(type, currency, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderData)) return false;
        OrderData orderData = (OrderData) o;
        return Objects.equals(amount, orderData.amount);
    }


    @Override
    public int compareTo(@NotNull ComparableOrderData o) {
        return getAmount() > o.getAmount() ? 1 : (getAmount() < o.getAmount() ? -1 : 0);
    }
}


class OrderData {
    enum Type {DELIVERY, PICKUP;}
    final Type type;
    final String currency;
    final double amount;

    OrderData(){
        this(Type.DELIVERY, "-", 0);
    }

    OrderData(@NotNull Type type,
              @NotNull String currency,
              @NotNull double amount) {
        this.type = type;
        this.currency = currency;
        this.amount = amount;
    }

    String getCurrency() {
        return currency;
    }

    double getAmount() {
        return amount;
    }

    Type getType() {
        return type;
    }

    public String toString() {
        return this.type + " " + this.currency + " " + this.amount;
    }
}

public class Orders {

    static Map<String, Double> getMaxMinusMinDeliveryMapByCurrency(List<ComparableOrderData> orderDataList) {

        // сохранить только заказы с типом DELIVERY
        List<ComparableOrderData> newOrderDataList = orderDataList.stream().
                filter((o) -> o.type == OrderData.Type.DELIVERY).collect(Collectors.toList());

        // выделяем типы currency
        List<String> curr = newOrderDataList.stream().map(OrderData::getCurrency).
                distinct().collect(Collectors.toList());

        Map<String, Double> result = new LinkedHashMap<>();


        for (int i = 0; i < curr.size(); i++){
            String c = curr.get(i);

            Double min = newOrderDataList.stream().filter((o) -> o.currency == c).
                    min((o1, o2) -> o1.compareTo(o2)).map(OrderData::getAmount).get();

            Double max = newOrderDataList.stream().filter((o) -> o.currency == c).
                    max((o1, o2) -> o1.compareTo(o2)).map(OrderData::getAmount).get();
            Double val = max - min; // если в валюте всего один заказ то разница равна 0

            result.put(curr.get(i), val);
        }

        return result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public static void print (Map<String, Double> list){
        String str = "[";

        for (Map.Entry entry: list.entrySet())
            str += "'" + entry.getKey() + "' -> "+ entry.getValue() + ", ";

        System.out.print(str.substring(0, (str.length()-2)) + "]");
    }

    public static void main(String[] args) {

        List<ComparableOrderData> list = new ArrayList<ComparableOrderData>();

        list.add(new ComparableOrderData(ComparableOrderData.Type.DELIVERY, "EUR", 2000));
        list.add(new ComparableOrderData(ComparableOrderData.Type.DELIVERY, "USD", 15));
        list.add(new ComparableOrderData(ComparableOrderData.Type.PICKUP, "USD", 55));
        list.add(new ComparableOrderData(ComparableOrderData.Type.DELIVERY, "USD", 35));
        list.add(new ComparableOrderData(ComparableOrderData.Type.DELIVERY, "RUB", 200));
        list.add(new ComparableOrderData(ComparableOrderData.Type.PICKUP, "RUB", 1250));
        list.add(new ComparableOrderData(ComparableOrderData.Type.DELIVERY, "RUB", 100));
        list.add(new ComparableOrderData(ComparableOrderData.Type.DELIVERY, "LIR", 20));
        list.add(new ComparableOrderData(ComparableOrderData.Type.DELIVERY, "LIR", 10));

        Map<String, Double> result = getMaxMinusMinDeliveryMapByCurrency(list);

        // вывод разницы в порядке возрастания
        print(result);
    }
}
