package lists;

import java.util.function.Function;

public interface IListVisitor<T, U> extends Function<IList<T>, U> {
    U visitEmpty(MtList<T> t);

    U visitCons(ConsList<T> t);
}
