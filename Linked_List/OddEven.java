package LinkedList;
class OddEven{
public void oddEven(){
     if(this.size==0 || this.size==1) return;
     
     Node op=new Node(-1);
     Node ev = new Node(-1);
     Node even=ev;
     Node odd=op;
     Node node = this.head;
     
     while(node!=null){
         if((node.data&1)==1){
             op.next=node;
             op=op.next;
         }else{
            ev.next=node;
            ev=ev.next;
         }
         
         node=node.next;
     }
     
     
     ev.next=null;
     op.next=null;
     
     //last odd to first even
     op.next=even.next;
     
     this.head=odd.next; //hadles cases for all even
     //if all are odd
     if(even.next==null){
         this.tail=op;
     }
     else{
         this.tail=ev;
     }
    }



    public void removeDuplicates(){
        
     if(this.size==0 || this.size==1) return;
     
     Node itr =this.head;
     Node node = itr.next;
     Node prev=itr;
     
     while(node!=null){
         if(itr.data!=node.data){
             prev.next=null;
             itr.next=node;
             itr=itr.next;
         }
         prev=node;
         node=node.next;
     }
    //  edge cases to avoid extra duplicates at end
     itr.next=null; 
     this.tail=itr;
  }

}
