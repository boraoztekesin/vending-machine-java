public class Token {
    String id;
    String type;
    int amount;
    int initial_adding_priority;
    public Token(String id,String type,int amount){
        this.id=id;
        this.type=type;
        this.amount=amount;
        this.initial_adding_priority= Integer.parseInt(id.substring(1));
    }
    public int getAmount(){
        return amount;
    }

}
