import java.util.*;

class Queue {

    ArrayList<Token> queue = new ArrayList<>();

    public void enqueue(Token token) {
            queue.add(token);
    }
    public void enqueue(Token token,int index){
            queue.add(index,token);
    }

    public void dequeue(Token token) {
        // if queue is empty
        if (queue.size()!=0) {
            queue.remove(token);
        }
    }
    //function to print the queues
    public void queueDisplay()
    {
        if (queue.size()==0) {
            return;
        }
        System.out.println("Token Box:");
        for (int h=queue.size()-1;h>=0;h--) {
            System.out.println(queue.get(h).id+" "+queue.get(h).type+" "+queue.get(h).amount+" ");
        }
    }
    public void sort(){
        Collections.sort(queue, new InitialSortingComparator());
    }
    //token process function does the token operations due to priorities
    public void token_process(Queue priority_queue,int wanted_amount,String item_part ){
        for (int p=0;p<priority_queue.queue.size();p++){

            Token t = priority_queue.queue.get(p);
            if(t.type.equals(item_part)){
                if(wanted_amount<=t.amount){
                    boolean to_the_last_index=false;
                    priority_queue.dequeue(t);
                    t.amount=t.amount-wanted_amount;
                    for (int f=0;f<priority_queue.queue.size();f++){

                        if(t.amount>priority_queue.queue.get(f).amount){
                            priority_queue.enqueue(t,f);
                            to_the_last_index=true;
                            break;
                        }
                    }
                    if(!to_the_last_index){
                        priority_queue.enqueue(t);
                    }
                    break;
                }
                else{
                    priority_queue.dequeue(t);
                    wanted_amount=wanted_amount-t.amount;
                    t.amount=0;
                    token_process(priority_queue,wanted_amount,item_part);


                }
            }}

    }
//custom comparator for sorting when the tokens were initially added to queue.
    static class InitialSortingComparator
            implements Comparator<Token> {
        public int compare(Token a, Token b) {
            if (a.amount != b.amount) {
                return -(a.amount - b.amount);
            }else {
                  return (a.initial_adding_priority- b.initial_adding_priority);

    }
    }
}
}
