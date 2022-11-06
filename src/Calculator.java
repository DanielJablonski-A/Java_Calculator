import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Calculator {
    public Calculator() {
        configure_table1();
        configure_mainDisplay();

        Button_0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("0");
            }
        });
        Button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("1");
            }
        });
        Button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("2");
            }
        });
        Button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("3");
            }
        });
        Button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("4");
            }
        });
        Button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("5");
            }
        });
        Button_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("6");
            }
        });
        Button_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("7");
            }
        });
        Button_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("8");
            }
        });
        Button_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("9");
            }
        });
        Button_coma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display(",");
            }
        });
        Button_percent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("%");
            }
        });
        Button_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("+");
            }
        });
        Button_substract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("-");
            }
        });
        Button_multiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("*");
            }
        });
        Button_divide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("/");
            }
        });
        Button_undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                String mainDisplay_text = get_main_Display_text();
                int mainDisplay_text_length = mainDisplay_text.length();
                if (mainDisplay_text_length > 0) {
                    mainDisplay_text = mainDisplay_text.substring(0, mainDisplay_text_length - 1);
                    show_on_main_Display(mainDisplay_text);
                }
            }
        });
        Button_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                show_on_main_Display("");
            }
        });
        Button_bracket_l.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("(");
            }
        });
        Button_bracket_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display(")");
            }
        });
        Button_square.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("²");
            }
        });
        Button_root.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                add_to_main_Display("√");
            }
        });

        Button_run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_main_display_color("black");
                String main_Display_text;
                String main_Display_text_original;
                Double result_int;
                String result_str;

                main_Display_text = get_main_Display_text();
                main_Display_text_original = main_Display_text;

                // SPRAWDZANIE POPRAWNOSCI PRZED LICZENIEM
                // zamieniam przecinki na kropki
                main_Display_text = coma_to_dot(main_Display_text);
                // sprawdzam dozwolone znaki
                find_in_allowed_symbols(main_Display_text);
                //sprawdzam z grubsza poprawnosc formuly
                check_for_errors_in_formula(main_Display_text);
                // dodaje brakujace znaki mnozenia
                main_Display_text = add_lazy_multiply_sign(main_Display_text);
                // zliczam i sprawdzam nawiasy w Stringu
                check_brackets(main_Display_text);

                // LICZENIA- KOLEJNOSC WYKONYWANIA DZIALAN
                // W NAWIASY
                //procent z ..
                main_Display_text = do_special_calculations_left(main_Display_text, "%");
                //potegowanie
                main_Display_text = do_special_calculations_left(main_Display_text, "²");
                //pierwiastkowanie
                main_Display_text = do_special_calculations_right(main_Display_text, "√");
                //nawiasy
                do_calculations_in_brackets(main_Display_text);
                main_Display_text = main_Display_text_tmp;
                System.out.println(main_Display_text);


                // jezeli nie ma juz nawiasow to licz co zostalo
                //procent z ..
                main_Display_text = do_special_calculations_left(main_Display_text, "%");
                //potegowanie
                main_Display_text = do_special_calculations_left(main_Display_text, "²");
                //pierwiastkowanie
                main_Display_text = do_special_calculations_right(main_Display_text, "√");
                // zliczaj prosta calosc
                main_Display_text = make_sequence_of_arythmetic_calculations(main_Display_text);

                // pokaz na ekranie i historii
                System.out.println(main_Display_text);
                show_on_main_Display(main_Display_text);
                DefaultTableModel model = (DefaultTableModel) get_table1().getModel();
                model.addRow(new Object[]{"", main_Display_text_original, "=", main_Display_text});
                get_table1().setModel(model);

                // przesun historie w dol
                int height = updateTable1Height(get_table1(), 1);
                jsp.revalidate();
                jsp.getVerticalScrollBar().setValue(height);

                // wyczysc bledy
                error_display.setText("");
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JTable source = (JTable)e.getSource();
                int row_index = source.rowAtPoint( e.getPoint() );
                int col_index = source.columnAtPoint( e.getPoint() );
                Object obj_value = get_table1().getModel().getValueAt(row_index, col_index);
                String cell_value = obj_value.toString();
                if (!cell_value.equals("=")) {
                    show_on_main_Display(cell_value);
                }
            }
        });
    }

    public String do_special_calculations_left(String main_Display_text, String special_sign){
        // szukam znaku "specjalnego"
        List<Integer> todo_list = new ArrayList<Integer>();
        for (int index = main_Display_text.indexOf(special_sign); index >= 0; index = main_Display_text.indexOf(special_sign, index + 1))
        {
            todo_list.add(index);
        }

        // brak to wychodz z funkcji
        if (todo_list.size() == 0) return main_Display_text;

        int index = 0;
        for (int i = 0; i < todo_list.size(); i++){
            for (int k = main_Display_text.indexOf(special_sign); k >= 0; k = main_Display_text.indexOf(special_sign, k + 1))
            {
                if (k > index) {
                    index = k;
                    break;
                }
            }
            //int index = todo_list.get(i);
            // sprawdzam czy przed znakiem jest koniec nawiasu
            if (main_Display_text.substring(index - 1, index).equals(")")){
                // jezeli tak to znaczy ze nawias w nawiasie wiec ten pozniej bedziemy robic
                continue;
            }
            // obliczam
            String number_string = "";
            int number_string_position_start = 0;
            for (int j = index; j>0; j--){
                if (find_in_sign_array_list_with_brackets(main_Display_text.substring(j-1, j)) == 1){
                    // poprawka na minus z przodu
                    if (main_Display_text.substring(j-1, j).equals("-")){
                        if (main_Display_text.substring(j-2, j).equals("(-") || j-1 == 0){
                            number_string = main_Display_text.substring(j-1, index);
                            number_string_position_start = j-1;
                        }
                    }
                    break;
                }
                number_string = main_Display_text.substring(j-1, index);
                number_string_position_start = j-1;
            }
            //System.out.println(number_string);
            double number_int = Double.parseDouble(number_string);
            String before_sign = "";
            if (number_int < 0) before_sign = "-";
            if (special_sign.equals("²")){
                number_int = Math.pow(number_int, 2);
                if (before_sign.equals("-")){number_int *= (-1);}
            }
            if (special_sign.equals("%")){
                number_int = number_int/100;
            }
            number_string = String.valueOf(number_int);
            //podmieniam
            main_Display_text = substitutionByIndex(main_Display_text, number_string_position_start, index+1, number_string);
        }
        System.out.println(main_Display_text);
        return main_Display_text;
    }

    public String do_special_calculations_right(String main_Display_text, String special_sign){
        // szukam znaku "specjalnego"
        List<Integer> todo_list = new ArrayList<Integer>();
        for (int index = main_Display_text.indexOf(special_sign); index >= 0; index = main_Display_text.indexOf(special_sign, index + 1))
        {
            // sprawdzam czy za znakiem jest poczatek nawiasu
            if (main_Display_text.substring(index+1, index + 2).equals("(")){
                // jezeli tak to pozniej to bedziemy robic
                continue;
            }
            todo_list.add(index);
        }

        // brak to wychodz z funkcji
        if (todo_list.size() == 0) return main_Display_text;

        for (int i = 0; i < todo_list.size(); i++){
            int index = todo_list.get(i);

            index++;
            // szukam
            String number_string = "";
            int number_string_position_end = 0;
            for (int j = index; j < main_Display_text.length(); j++){
                if (find_in_sign_array_list_with_brackets(main_Display_text.substring(j, j+1)) == 1){
                    break;
                }
                number_string = main_Display_text.substring(index, j+1);
                number_string_position_end = j+1;
            }
            index--;

            // obliczam
            double number_int = Double.parseDouble(number_string);
            if (special_sign == "√"){
                number_int = Math.sqrt(number_int);
            }
            number_string = String.valueOf(number_int);

            //podmieniam
            main_Display_text = substitutionByIndex(main_Display_text, index, number_string_position_end, number_string);
        }
        //System.out.println(main_Display_text);
        return main_Display_text;
    }


    String main_Display_text_tmp;
    public void do_calculations_in_brackets(String main_Display_text){
        List<Integer> bracket_l_list = new ArrayList<Integer>();
        List<Integer> bracket_p_list = new ArrayList<Integer>();
        for (int index = main_Display_text.indexOf("("); index >= 0; index = main_Display_text.indexOf("(", index + 1))
        {
            bracket_l_list.add(index);
        }
        for (int index = main_Display_text.indexOf(")"); index >= 0; index = main_Display_text.indexOf(")", index + 1))
        {
            bracket_p_list.add(index);
        }
        int bracket_list_size = bracket_l_list.size();
        int i = 0;
        String bracket_result;
        for (int j = 0; j < bracket_list_size; j++){
            if (bracket_l_list.get(j) > bracket_p_list.get(i)){
                // jezeli jest wiecej niz 2 nawiasy oraz nawiasy w nawiasach
                //System.out.println(bracket_l_list.get(j-1));
                //System.out.println(bracket_p_list.get(i));
                bracket_result = make_sequence_of_arythmetic_calculations(main_Display_text.substring(bracket_l_list.get(j-1), bracket_p_list.get(i)+1));
                // sprawdzamy czy sa jeszcze jakies znaki tam gdzie chcemy robic
                if (bracket_l_list.get(j-1)-1 >= 0){
                    // jezeli przed nawiasem nie ma żadnego znaku stawiaj znak mnozenia
                    if (find_in_sign_array_list(main_Display_text.substring(bracket_l_list.get(j-1)-1, bracket_l_list.get(j-1))) == 0){
                        bracket_result = "*" + bracket_result;
                    }
                }
                main_Display_text = substitutionByIndex(main_Display_text, bracket_l_list.get(j-1), bracket_p_list.get(i)+1, bracket_result);
                bracket_l_list.remove(j-1);
                bracket_p_list.remove(i);
                break;
            } else if ((j+1 == bracket_list_size)){
                // jezeli został ostatni nawias obejmujacy wszystko, badz
                // ostatni nawias obejmujacy wszystko oraz ostatni nawias w tym nawiasie
                //System.out.println(bracket_l_list.get(j));
                //System.out.println(bracket_p_list.get(i));
                bracket_result = make_sequence_of_arythmetic_calculations(main_Display_text.substring(bracket_l_list.get(j), bracket_p_list.get(i)+1));
                // sprawdzamy czy sa jeszcze jakies znaki tam gdzie chcemy robic
                if (bracket_l_list.get(j)-1 >= 0){
                    // jezeli przed nawiasem nie ma żadnego znaku stawiaj znak mnozenia
                    if (find_in_sign_array_list(main_Display_text.substring(bracket_l_list.get(j)-1, bracket_l_list.get(j))) == 0) {
                        bracket_result = "*" + bracket_result;
                    }
                }
                main_Display_text = substitutionByIndex(main_Display_text, bracket_l_list.get(j), bracket_p_list.get(i)+1, bracket_result);

                bracket_l_list.remove(j);
                bracket_p_list.remove(i);
                break;
            }
        }
        main_Display_text_tmp = main_Display_text;
        if (bracket_l_list.size() > 0){
            do_calculations_in_brackets(main_Display_text);
        }
    }

    List<String> to_do = new ArrayList<String>();

    public String calculate_normal(String string_to_calculate, String symbol){
        // usuwam nawiasy jezeli sa
        if (string_to_calculate.substring(0, 1).equals("(") && string_to_calculate.substring(string_to_calculate.length()-1, string_to_calculate.length()).equals(")")){
            string_to_calculate = removeByIndex(string_to_calculate, 0, 1);
            string_to_calculate = removeByIndex(string_to_calculate, string_to_calculate.length()-1, string_to_calculate.length());
        }

        //System.out.println(string_to_calculate);

        to_do.clear();
        int start_from = 0;
        String numbers = "";
        // zamieniam string z wyswietlacza na tablice liczb i znakow
        for (int i = 1; i <= string_to_calculate.length(); i++) {
            String sign;
            if (string_to_calculate.substring(i-1, i).equals(".")){continue;} // poprawka na double
            // + wyjatek jak pierwsza litera ze znakiem minus
            if (isNumeric(string_to_calculate.substring(start_from, i)) || (string_to_calculate.substring(start_from, i).equals("-"))) {
                numbers = string_to_calculate.substring(start_from, i);
            } else {
                sign = string_to_calculate.substring(i-1, i);
                to_do.add(numbers);
                to_do.add(sign);
                start_from = i;
                numbers = "";
            }
            // obsluga ostatnich liczb w szeregu
            if (i == string_to_calculate.length()){
                to_do.add(numbers);
            }
        }
        //System.out.println(to_do);

        // szukam wyrazenia z String symbol
        String left_side = "";
        String right_side = "";
        for (int i = 0; i < to_do.size(); i++){
            if (to_do.get(i).equals(symbol)){
                left_side = to_do.get(i-1);
                right_side = to_do.get(i+1);
                // wykonuje obliczanie
                double first_double = Double.parseDouble(left_side);
                double second_double = Double.parseDouble(right_side);
                double result_double = 0.0;
                //System.out.println(symbol);
                switch (symbol) {
                    case "+":
                        result_double = first_double + second_double;
                        break;
                    case "-":
                        result_double = first_double - second_double;
                        break;
                    case "*":
                        result_double = first_double * second_double;
                        break;
                    case "/":
                        result_double = first_double / second_double;
                        break;
                }
                // zamieniam na string
                String result_string = String.valueOf(result_double);
                // podstawiam obliczone
                //string_to_calculate = string_to_return;
                to_do.set(i-1, ""); // zeby niepsuc tablicy ustawiam jedna z policzonych liczb jako pusty element tablicy
                to_do.set(i, ""); // zeby nie psuc tablicy ustawiam znak jako pusty element tablicy
                to_do.set(i+1, result_string); // obliczona wartosc przypisuje drugiej liczbie ktora byla w dzialaniu
                //System.out.println(result_string);
            }
        }

        // zamieniam spowrotem tablice to_do na string i go zwracam
        StringBuilder string_builder = new StringBuilder();
        for (int i = 0; i < to_do.size(); i++){
            string_builder.append(to_do.get(i));
        }
        String string_to_return = string_builder.toString();

        //System.out.println(string_to_return);
        return string_to_return;
    }

    public String make_sequence_of_arythmetic_calculations(String string_to_calculate){
        string_to_calculate = do_special_calculations_left(string_to_calculate, "%");
        string_to_calculate = do_special_calculations_left(string_to_calculate, "²");
        string_to_calculate = do_special_calculations_right(string_to_calculate, "√");
        string_to_calculate = calculate_normal(string_to_calculate, "*");
        string_to_calculate = calculate_normal(string_to_calculate, "/");
        string_to_calculate = calculate_normal(string_to_calculate, "+");
        string_to_calculate = calculate_normal(string_to_calculate, "-");
        return string_to_calculate;
    }

    public List before_sign_that_need_multiply_sign(){
        List<String> sign_array_list = new ArrayList<String>();
        sign_array_list.add("%");
        sign_array_list.add("²");
        sign_array_list.add(")");
        return sign_array_list;
    }

    public List get_all_allowed_symbols(){
        List<String> sign_array_list = new ArrayList<String>();
        sign_array_list.add("%");
        sign_array_list.add("+");
        sign_array_list.add("-");
        sign_array_list.add("*");
        sign_array_list.add("/");
        sign_array_list.add("²");
        sign_array_list.add("√");
        sign_array_list.add("(");
        sign_array_list.add(")");
        sign_array_list.add("1");
        sign_array_list.add("2");
        sign_array_list.add("3");
        sign_array_list.add("4");
        sign_array_list.add("5");
        sign_array_list.add("6");
        sign_array_list.add("7");
        sign_array_list.add("8");
        sign_array_list.add("9");
        sign_array_list.add("0");
        sign_array_list.add(".");
        return sign_array_list;
    }

    public List get_sign_array_list(){
        List<String> sign_array_list = new ArrayList<String>();
        sign_array_list.add("%");
        sign_array_list.add("+");
        sign_array_list.add("−");
        sign_array_list.add("*");
        sign_array_list.add("/");
        sign_array_list.add("²");
        sign_array_list.add("√");
        return sign_array_list;
    }

    public List get_sign_array_list_with_brackets(){
        List<String> sign_array_list = new ArrayList<String>();
        sign_array_list.add("%");
        sign_array_list.add("+");
        sign_array_list.add("−");
        sign_array_list.add("*");
        sign_array_list.add("/");
        sign_array_list.add("²");
        sign_array_list.add("√");
        sign_array_list.add("(");
        sign_array_list.add(")");
        return sign_array_list;
    }

    public String coma_to_dot(String string){
        string = string.replaceAll(",", ".");
        return string;
    }

    public void find_in_allowed_symbols(String string){
        if (string.length() == 0){
            show_error_on_main_display("Pusto.");
        }
        List sign_array_list = get_all_allowed_symbols();
        for (int i = 0; i < string.length(); i++) {
            // sprawdzaj dozwolone znaki
            String symbol = string.substring(i, i+1);
            int in_allowed_symbols = 0;
            for (int j = 0; j < sign_array_list.size(); j++){
                if (sign_array_list.get(j).equals(symbol)) in_allowed_symbols = 1;
            }
            if (in_allowed_symbols == 0){
                show_error_on_main_display("Niedozwolone znaki.");
            }
        }
    }

    public void check_for_errors_in_formula(String string){
        // rozbijam do tablicy wszystkie znaki
        List<String> to_do = new ArrayList<String>();
        for (int i = 0; i < string.length(); i++) {
            // sprawdzaj dozwolone znaki
            String symbol = string.substring(i, i + 1);
            to_do.add(symbol);
        }
        // sprawdzam czy miedzy znakami sa liczby
        for (int i = 0; i < to_do.size(); i++){
            //int prev_symbol_int_val = Integer.parseInt(to_do.get(i-1));
            //znak potegi
            if (to_do.get(i).equals("²")){
                if (i == 0 || (!isNumeric(to_do.get(i-1))) && !to_do.get(i-1).equals(")")){
                    set_main_display_color("red");
                    show_error_on_main_display("Zla formula przy znaku '²'.");
                }
            }
            // znak pierwiastkowania
            if (to_do.get(i).equals("√")){
                if ((i+1 == to_do.size()) || (!isNumeric(to_do.get(i+1)) && !to_do.get(i+1).equals("("))){
                    set_main_display_color("red");
                    show_error_on_main_display("Zla formula przy znaku '√'.");
                }
            }
            // znak procentu
            if (to_do.get(i).equals("%")){
                if (i == 0 || (!isNumeric(to_do.get(i-1))) && !to_do.get(i-1).equals(")")){
                    set_main_display_color("red");
                    show_error_on_main_display("Zla formula przy znaku '%'.");
                }
            }
            // nawiasy
            if (to_do.get(i).equals(")")){
                if (i == 0 || (!isNumeric(to_do.get(i-1))) && !to_do.get(i-1).equals("²") && !to_do.get(i-1).equals("%")){
                    set_main_display_color("red");
                    show_error_on_main_display("Zla formula przy znaku ')'.");
                }
            }
            // znak mnozenia
            if (to_do.get(i).equals("*")){
                if ((i == 0 || i == to_do.size()-1) || (!isNumeric(to_do.get(i+1)) && !to_do.get(i+1).equals("(") && !to_do.get(i+1).equals("√"))){
                    set_main_display_color("red");
                    show_error_on_main_display("Zla formula przy znaku '*'.");
                }
            }
            // znak dzielenia
            if (to_do.get(i).equals("/")){
                if ((i == 0 || i == to_do.size()-1) || (!isNumeric(to_do.get(i+1)) && !to_do.get(i+1).equals("(") && !to_do.get(i+1).equals("√"))){
                    set_main_display_color("red");
                    show_error_on_main_display("Zla formula przy znaku '/'.");
                }
            }
            // znak plus
            if (to_do.get(i).equals("+")){
                if ((i == 0 || i == to_do.size()-1) || (!isNumeric(to_do.get(i+1)) && !to_do.get(i+1).equals("(") && !to_do.get(i+1).equals("√"))){
                    set_main_display_color("red");
                    show_error_on_main_display("Zla formula przy znaku '+'.");
                }
            }
            // znak minus
            if (to_do.get(i).equals("-")){
                if ((i == to_do.size()-1) || (!isNumeric(to_do.get(i+1)) && !to_do.get(i+1).equals("(") && !to_do.get(i+1).equals("√"))){
                    set_main_display_color("red");
                    show_error_on_main_display("Zla formula przy znaku '-'.");
                }
            }
        }
    }

    public String add_lazy_multiply_sign(String string){
        // rozbijam do tablicy wszystkie znaki
        List<String> to_do = new ArrayList<String>();
        for (int i = 0; i < string.length(); i++) {
            // sprawdzaj dozwolone znaki
            String symbol = string.substring(i, i + 1);
            to_do.add(symbol);
        }
        List<Integer> place_to_add = new ArrayList<Integer>();
        for (int i = 0; i < to_do.size(); i++){
            if (i != 0 && to_do.get(i).equals("(")){
                String[] values = {"+", "-", "*", "/", "√", "("};
                boolean contains = Arrays.stream(values).anyMatch(to_do.get(i-1)::equals);
                if (!contains){
                    place_to_add.add(i);
                }
            }
            if (i+1 != to_do.size() && to_do.get(i).equals(")")){
                String[] values = {"+", "-", "*", "/", "²", "%", ")"};
                boolean contains = Arrays.stream(values).anyMatch(to_do.get(i+1)::equals);
                if (!contains){
                    place_to_add.add(i+1);
                }
            }
            if (i+1 != to_do.size() && to_do.get(i).equals("%")){
                String[] values = {"+", "-", "*", "/", "²", ")"};
                boolean contains = Arrays.stream(values).anyMatch(to_do.get(i+1)::equals);
                if (!contains){
                    place_to_add.add(i+1);
                }
            }
            if (i+1 != to_do.size() && to_do.get(i).equals("²")){
                String[] values = {"+", "-", "*", "/", "²", "%", ")"};
                boolean contains = Arrays.stream(values).anyMatch(to_do.get(i+1)::equals);
                if (i != 0 && !contains){
                    place_to_add.add(i+1);
                }
            }
            if (i != 0 && to_do.get(i).equals("√")){
                String[] values = {"+", "-", "*", "/", "√", "("};
                boolean contains = Arrays.stream(values).anyMatch(to_do.get(i-1)::equals);
                if (!contains){
                    place_to_add.add(i);
                }
            }
        }
        if (place_to_add.size() != 0){
            place_to_add.sort(Comparator.reverseOrder());
            //System.out.println(place_to_add);
            int i_tmp = 0;
            for(Integer i : place_to_add){
                if (i == i_tmp){continue;}
                i_tmp = i;
                to_do.add(i, "*");
            }
            string = String.join("", to_do);
        }
        //System.out.println(string);
        return string;
    }

    public int find_in_before_sign(String string){
        List sign_array_list = before_sign_that_need_multiply_sign();
        for (int i = 0; i < sign_array_list.size(); i++){
            if (sign_array_list.get(i).equals(string)) return 1;
        }
        return 0;
    }

    public int find_in_sign_array_list(String string){
        List sign_array_list = get_sign_array_list();
        for (int i = 0; i < sign_array_list.size(); i++){
            if (sign_array_list.get(i).equals(string)) return 1;
        }
        return 0;
    }

    public int find_in_sign_array_list_with_brackets(String string){
        List sign_array_list = get_sign_array_list_with_brackets();
        for (int i = 0; i < sign_array_list.size(); i++){
            if (sign_array_list.get(i).equals(string)) return 1;
        }
        return 0;
    }

    public void add_to_main_Display(String value_str){
        String mainDisplay_text = get_main_Display_text();
        value_str = mainDisplay_text + value_str;
        show_on_main_Display(value_str);
    }

    public void show_on_main_Display(String value_str){
        mainDisplay.setText(value_str);
    }

    public String get_main_Display_text(){
        return mainDisplay.getText();
    }

    private String removeByIndex(String str, int i_start, int i_end){
        StringBuilder sb = new StringBuilder();
        if (i_start > 0) {
            sb.append(str, 0, i_start);
        }
        if (i_end < str.length() - 1) {
            sb.append(str, i_end, str.length());
        }
        return sb.toString();
    }

    private String substitutionByIndex(String str, int i_start, int i_end, String substitution){
        StringBuilder sb = new StringBuilder();
        if (i_start > 0) {
            sb.append(str, 0, i_start);
        }
        sb.append(substitution);
        if (i_end < str.length()) {
            sb.append(str, i_end, str.length());
        }
        return sb.toString();
    }

    public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void show_error_on_main_display(String string){
        set_main_display_color("red");
        error_display.setText(string);
        throw new java.lang.Error(string);
    }

    public void set_main_display_color(String color){
        if (color == "black"){
            mainDisplay.setForeground(Color.BLACK);
        } else if (color == "red"){
            mainDisplay.setForeground(Color.RED);
        }
    }

    public void check_brackets(String main_Display_text){
        List<Integer> bracket_l_list = new ArrayList<Integer>();
        for (int index = main_Display_text.indexOf("("); index >= 0; index = main_Display_text.indexOf("(", index + 1))
        {
            bracket_l_list.add(index);
        }
        List<Integer> bracket_p_list = new ArrayList<Integer>();
        for (int index = main_Display_text.indexOf(")"); index >= 0; index = main_Display_text.indexOf(")", index + 1))
        {
            bracket_p_list.add(index);
        }
        // obsluga bledow nawiasow
        if (!bracket_l_list.isEmpty() || !bracket_p_list.isEmpty()){
            if (bracket_l_list.size() != bracket_p_list.size()){
                set_main_display_color("red");
                show_error_on_main_display("Nie zamkniete nawiasy.");
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().Calculator_bg);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        // favicon
        ImageIcon icon = new ImageIcon("favicon.png");
        frame.setIconImage(icon.getImage());
    }

    public JTable get_table1() {
        return table1;
    }

    public void configure_mainDisplay(){
        //mainDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        //mainDisplay.setVerticalAlignment(SwingConstants.TOP);
        mainDisplay.setBackground(Color.white);
        mainDisplay.setOpaque(true);
        Border margin = new EmptyBorder(5,10,0,0);
        Border border = new MatteBorder(1, 0, 0, 0, Color.DARK_GRAY);
        mainDisplay.setBorder(new CompoundBorder(border, margin));

        error_display.setHorizontalAlignment(SwingConstants.LEFT);
        error_display.setVerticalAlignment(SwingConstants.BOTTOM);
        error_display.setBackground(Color.white);
        error_display.setOpaque(true);
        margin = new EmptyBorder(0,10,0,0);
        border = new MatteBorder(0, 0, 1, 0, Color.DARK_GRAY);
        error_display.setBorder(new CompoundBorder(border, margin));
    }

    public void configure_table1(){
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        get_table1().setDefaultEditor(Object.class, null);
        get_table1().getTableHeader().setUI(null);
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableModel model = (DefaultTableModel) get_table1().getModel();
        model.addColumn("margin");
        model.addColumn("history");
        model.addColumn("equals");
        model.addColumn("result");

        get_table1().setModel(model);
        get_table1().getColumnModel().getColumn(0).setPreferredWidth(5);
        get_table1().getColumnModel().getColumn(0).setMaxWidth(5);
        get_table1().getColumnModel().getColumn(1).setMinWidth(190);
        //get_table1().getColumnModel().getColumn(1).setPreferredWidth(10);
        get_table1().getColumnModel().getColumn(2).setMaxWidth(20);
        get_table1().getColumnModel().getColumn(3).setPreferredWidth(100);
        get_table1().getColumnModel().getColumn(3).setMaxWidth(100);
        get_table1().getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jsp.setViewportView(get_table1());
        jsp.setBorder(BorderFactory.createEmptyBorder());
        jsp.getVerticalScrollBar().setUI(new MyScrollBarUI());
    }

    private int updateTable1Height(JTable table, int addextra) {
        int basic_height = 120;
        int new_height = 0;
        for (int row = 0; row < table.getRowCount(); row++)
        {
            int rowHeight = table.getRowHeight();
            new_height += rowHeight;
            if (row == table.getRowCount()-1){
                new_height += rowHeight*addextra;
            }
        }
        if (new_height < basic_height){
            new_height = basic_height;
        }
        table.setPreferredSize(new Dimension(120, new_height));
        return new_height;
    }

    private JPanel Calculator_bg;
    private JButton Button_percent;
    private JButton Button_8;
    private JButton Button_3;
    private JButton Button_6;
    private JButton Button_9;
    private JButton Button_5;
    private JButton Button_2;
    private JButton Button_coma;
    private JButton Button_divide;
    private JButton Button_multiply;
    private JButton Button_substract;
    private JButton Button_add;
    private JButton Button_undo;
    private JButton Button_bracket_l;
    private JButton Button_square;
    private JButton Button_run;
    private JButton Button_clear;
    private JButton Button_7;
    private JButton Button_4;
    private JButton Button_1;
    private JButton Button_0;
    private JButton Button_bracket_p;
    private JButton Button_root;
    private JTable table1;
    private JScrollPane jsp;
    private JLabel error_display;
    private JTextField mainDisplay;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
