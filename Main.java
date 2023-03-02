import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
public class Main {
    static int initial_token_count=0;
    static ArrayList<Token> token_list=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        PrintStream out ;
        out= new PrintStream(new FileOutputStream(args[4]));
        System.setOut(out);
        BufferedReader in;
        in = new BufferedReader(new FileReader(args[0]));
        ArrayList<Part> parts_list =new ArrayList<>();
        String part;
        while ((part = in.readLine()) != null) {
            //creating a stack for every part : every part has a stack to store its items
            String stack_name=part;
            Stack stack1= new Stack();
            Part part1=new Part(stack1,stack_name);
            parts_list.add(part1);
        }
        in = new BufferedReader(new FileReader(args[1]));
        String string;
        while ((string = in.readLine()) != null) {
            String[] arr= string.split(" ");
            String item_id=arr[0];
            String part_name=arr[1];
            Item itemm=new Item(item_id,part_name);
            //pushing items to their part's stack
            for (Part p: parts_list){
                if(p.stack_name.equals(itemm.items_part)){
                    p.stack.push(itemm);
                }
            }
        }
        in = new BufferedReader(new FileReader(args[2]));
        while ((string = in.readLine()) != null) {
            String[] arr = string.split(" ");
            Token token = new Token(arr[0], arr[1], Integer.parseInt(arr[2]));
            token_list.add(token);
            initial_token_count++;
        }
        Queue priority_queue=new Queue();
        //enqueue operation of the tokens
        for (Token t:token_list){
            priority_queue.enqueue(t);
        }
        //putting tokens in the queue with initially sorting the tokens with respect to their amounts and their adding times.
        priority_queue.sort();
        in = new BufferedReader(new FileReader(args[3]));
        while ((string = in.readLine()) != null) {
            String[] arr = string.split("\t");
            String command=arr[0];
            if (command.equals("BUY")){
                for (int z=1;z<arr.length;z++){
                    String[] temp_arr=arr[z].split(",");
                    String item_part=temp_arr[0];
                    int wanted_amount=Integer.parseInt(temp_arr[1]);
                    //bought items removed from stacks
                    for (Part p:parts_list){
                        if(p.stack_name.equals(item_part)){
                            for (int j=0;j<wanted_amount;j++){
                                p.stack.pop();
                            }
                        }
                    }
                    //function below(token_process) does the token operations for buying
                    priority_queue.token_process(priority_queue,wanted_amount,item_part);
                }
            }else if(command.equals("PUT")){
                for (int z=1;z<arr.length;z++){
                    String[] temp_arr=arr[z].split(",");
                    String item_partt=temp_arr[0];
                    for(int p=1;p<temp_arr.length;p++){
                        for (Part parts:parts_list){
                            if(item_partt.equals(parts.stack_name)){
                                Item new_item=new Item(temp_arr[p],item_partt);
                                parts.stack.push(new_item);
                            }
                        }

                    }
                }
            }
        }
        //finally printing
        for (Part ps:parts_list){
            System.out.println(ps.stack_name+":");
            ps.stack.display();

            System.out.println("---------------");
        }
        priority_queue.queueDisplay();
        in.close();
    }
}