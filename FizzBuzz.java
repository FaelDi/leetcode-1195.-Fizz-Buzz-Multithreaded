class FizzBuzz {
    private int n;
    private Semaphore sFizz = new Semaphore(0);
    private Semaphore sBuzz = new Semaphore(0);
    private Semaphore sFizzBuzz = new Semaphore(0); 
    private Semaphore sNumber = new Semaphore(1); 
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i=1;i<=this.n;i++){
            if(i%3 == 0 && i%5 != 0){
                sFizz.acquire();
                printFizz.run();
                nextSemaphoreRelease(i);
            }
        } 
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i=1;i<=this.n;i++){
            if(i%5 == 0 && i%3 != 0){
                sBuzz.acquire();
                printBuzz.run();
                nextSemaphoreRelease(i);
            }
        } 
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i=1;i<=this.n;i++){
            if(i%15 == 0){
                sFizzBuzz.acquire();
                printFizzBuzz.run();
                nextSemaphoreRelease(i);
            }
        } 
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=this.n;i++){
            if(i%3 !=0 && i%5 != 0){
                sNumber.acquire();
                printNumber.accept(i);
                nextSemaphoreRelease(i);
            }
        }
    }

    private void nextSemaphoreRelease(int num) {
        num++;
        if(num%15 == 0){
            sFizzBuzz.release();
        }else if(num%5 == 0){
            sBuzz.release(); 
        }else if(num%3 == 0){
            sFizz.release();
        }else{
            sNumber.release();
        }
    }
}
