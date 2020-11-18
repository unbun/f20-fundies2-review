package utils;

import java.util.ArrayList;
import java.util.Arrays;
import lists.ConsList;
import lists.IList;
import lists.MtList;

public class Utils {

    public static <T> void printObject(String header, T obj) {
        System.out.println(header);
        underline(header.length());
        System.out.println(obj.toString());
        underline(header.length());
        System.out.println();
    }

    private static void underline(int nn) {
        for(int ii = 0; ii < nn; ii++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public static <T> void printIterable(String header, Iterable<T> iter){
        System.out.println(header);
        underline(header.length());

        int ii = 0;
        for(T item : iter){
            System.out.println(String.format("[%d] %s", ii, item.toString()));
            ii++;
        }
        underline(header.length());
        System.out.println();
    }

    public static <T> ArrayList<T> makeList(T ... tlist) {
        return new ArrayList<>(Arrays.asList(tlist));
    }

    public static <T> IList<T> makeILo(T ... tlist) {
        IList<T> build = new MtList<>();
        for(int ii = tlist.length - 1; ii >= 0; ii--){
            build = new ConsList<>(tlist[ii], build);
        }
        return build;
    }

}
