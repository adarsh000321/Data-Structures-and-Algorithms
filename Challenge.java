
import java.util.*;

public class Main{
    public static void main(String[] args) throws java.lang.Exception {
        //test-1
        LinkedHashMap<String,Integer> map=new LinkedHashMap<>();
        map.put("2019-01-01",100);map.put("2019-01-04",115);
        System.out.println(solution(map));
        //test-2
        map = new LinkedHashMap<>();
        map.put("2019-01-10",10);map.put("2019-01-11",20);map.put("2019-01-12",15);map.put("2019-01-13",10);
        System.out.println(solution(map));
    }
    static String solution(LinkedHashMap<String,Integer> map){// map is used to store (key,value) pair
        try {
            Map.Entry<String, Integer> prev = null;
            LinkedHashMap<String, Double> ans = new LinkedHashMap<>();
            int days[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                String s = e.getKey();
                if (prev != null) {
                    int from[] = new int[3], to[] = new int[3]; //index 0 = year, 1 = month, 2 = day
                    String x[] = prev.getKey().split("-");
                    for (int i = 0; i < 3; i++) {
                        from[i] = Integer.parseInt(x[i]);
                    }
                    x = s.split("-");
                    for (int i = 0; i < 3; i++) {
                        to[i] = Integer.parseInt(x[i]);
                    }
                    int year = from[0], month = from[1], day = from[2];
                    ArrayList<String> dates = new ArrayList<>();
                    int cnt = 0;
                    while (true) {
                        if (year == to[0] && month == to[1] && day == to[2]) break;
                        if ((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) days[2] = 29; // leap year
                        while (true) {
                            if (year == to[0] && month == to[1] && day == to[2]) break;
                            if (month == 13) {
                                month = 1;
                                break;
                            }
                            while (true) {
                                if (year == to[0] && month == to[1] && day == to[2]) break;
                                if (day == days[month] + 1) {
                                    day = 1;
                                    break;
                                }
                                ;
                                dates.add(year + "-" + ((month + "").length() == 1 ? "0" : "") + month + "-" + ((day + "").length() == 1 ? "0" : "") + day);
                                cnt++;
                                day++;
                            }
                            if (year == to[0] && month == to[1] && day == to[2]) break;
                            month++;
                        }
                        if (year == to[0] && month == to[1] && day == to[2]) break;
                        year++;
                    }
                    if (dates.size() == 0) break;
                    double avg = (double) (e.getValue() - prev.getValue()) / dates.size();
                    double start = prev.getValue();
                    for (String y : dates) {
                        ans.put(y, start);
                        start += avg;
                    }

                }
                prev = e;
            }
            ans.put(prev.getKey(), (double) prev.getValue());
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (Map.Entry<String, Double> e : ans.entrySet()) {
                sb.append(e.getKey() + ":" + e.getValue() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);//delete extra ,
            sb.append("}");
            return sb.toString();
        }catch (Error e){
            throw  new Error("Invalid data provided");
        }
    }

}
