package ua.nure.tsomkalov.Task2;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ListImpl implements List {

    private class it implements Iterator {

        int current = -1;

        @Override
        public void remove() {
            ListImpl.this.remove(current);
            current--;
            hasNext();
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return arr[++current];
            }

            return null;
        }

        @Override
        public boolean hasNext() {
            if (current + 1 < size()) {
                return true;
            } else {
                current = -1;
                return false;
            }
        }
    }

    private Object[] arr;

    Iterator it = new it();

    private void resize() {
        arr = Arrays.copyOf(arr, arr.length + 10);
    }

    public ListImpl() {
        arr = new Object[10];
    }

    @Override
    public void add(Object el) {
        for (int i = 0; ; i++) {
            if (arr[i] == null) {
                arr[i] = el;
                break;
            }
            if (i == arr.length - 1) {
                resize();
            }
        }
    }

    @Override
    public void addAll(List list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size());
    }

    @Override
    public void clear() {
        arr = new Object[10];
    }

    @Override
    public boolean contains(Object el) {
        for (int i = 0; i < size(); i++) {
            if (arr[i].equals(el)) return true;
        }
        return false;
    }

    @Override
    public Object get(int index) {
        if (index < size()) {
            return arr[index];
        } else {
            return null;
        }
    }

    @Override
    public int indexOf(Object el) {
        for (int i = 0; i < size(); i++) {
            if (arr[i].equals(el)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object remove(int j) {
        Object res;
        if (j < size()) {
            res = arr[j];
            int length = arr.length - j - 1;
            System.arraycopy(arr, j + 1, arr, j, length);
        } else {
            res = null;
        }

        return res;
    }

    @Override
    public boolean remove(Object el) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == el) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                res++;
            }
        }

        return res;
    }

    @Override
    public Iterator iterator() {
        return it;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size(); i++) {
            sb.append(arr[i]);
            if (i < size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("~~~ list A B C");
        System.out.println("~~~ Result: [A, B, C]");
        List list = new ListImpl();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list);

        System.out.println("~~~ list2: D E F");
        System.out.println("~~~ Result: [D, E, F]");
        List list2 = new ListImpl();
        list2.add("D");
        list2.add("E");
        list2.add("F");
        System.out.println(list2);

        System.out.println("~~~ list.addAll(list2)");
        System.out.println("~~~ Result: [A, B, C, D, E, F]");
        list.addAll(list2);
        System.out.println(list);

        System.out.println("~~~ list.add(C)");
        System.out.println("~~~ Result: [A, B, C, D, E, F, C]");
        list.add("C");
        System.out.println(list);

        System.out.println("~~~ list.clear()");
        System.out.println("~~~ Result: []");
        list.clear();
        System.out.println(list);

        System.out.println("~~~ list.addAll(list2)");
        System.out.println("~~~ Result: [D, E, F]");
        list.addAll(list2);
        System.out.println(list);

        System.out.println("~~~ list.contains(E)");
        System.out.println("~~~ Result: true");
        System.out.println(list.contains("E"));

        System.out.println("~~~ list.contains(С)");
        System.out.println("~~~ Result: false");
        System.out.println(list.contains("C"));

        System.out.println("~~~ list.indexOf(D)");
        System.out.println("~~~ Result: 0");
        System.out.println(list.indexOf("D"));

        System.out.println("~~~ list.get(2)");
        System.out.println("~~~ Result: F");
        System.out.println(list.get(2));

        System.out.println("~~~ list.indexOf(F)");
        System.out.println("~~~ Result: 2");
        System.out.println(list.indexOf("F"));

        System.out.println("~~~ list.size()");
        System.out.println("~~~ Result: 3");
        System.out.println(list.size());

        System.out.println("~~~ list");
        System.out.println("~~~ Result: [D, E, F]");
        System.out.println(list);

        System.out.println("~~~ list.remove(1)");
        System.out.println("~~~ Result: [D, F]");
        list.remove(1);
        System.out.println(list);

        System.out.println("~~~ list.remove(F)");
        System.out.println("~~~ Result: [D]");
        list.remove("F");
        System.out.println(list);

        System.out.println("~~~ list.size()");
        System.out.println("~~~ Result: 1");
        System.out.println(list.size());

        System.out.println("~~~ list.addAll(list2)");
        System.out.println("~~~ Result: [D, D, E, F]");
        list.addAll(list2);
        System.out.println(list);

        System.out.println("~~~ foreach list");
        System.out.println("~~~ Result: D D E F");
        for (Object el : list) {
            System.out.print(el + " ");
        }
        System.out.println();

        System.out.println("~~~ Iterator it = list.iterator()");
        Iterator it = list.iterator();

        System.out.println("~~~ it.next()");
        System.out.println("~~~ Result: D");
        System.out.println(it.next());

        System.out.println("~~~ it.next()");
        System.out.println("~~~ Result: D");
        System.out.println(it.next());

        System.out.println("~~~ it.remove()");
        System.out.println("~~~ Result: [D, E, F]");
        it.remove();
        System.out.println(list);

        System.out.println("~~~ it.next()");
        System.out.println("~~~ Result: E");
        System.out.println(it.next());

        System.out.println("~~~ it.remove()");
        System.out.println("~~~ Result: [D, F]");
        it.remove();
        System.out.println(list);

        System.out.println("~~~ it.next()");
        System.out.println("~~~ Result: F");
        System.out.println(it.next());

        System.out.println("~~~ it.remove()");
        System.out.println("~~~ Result: [D]");
        it.remove();
        System.out.println(list);

        System.out.println("~~~ list.remove(D)");
        System.out.println("~~~ Result: []");
        list.remove("D");
        System.out.println(list);

        System.out.println("~~~ list.addAll(list2)");
        System.out.println("~~~ Result: [D, E, F]");
        list.addAll(list2);
        System.out.println(list);

        System.out.println("~~~ foreach list");
        System.out.println("~~~ Result: D E F ");
        for (Object el : list) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

}
