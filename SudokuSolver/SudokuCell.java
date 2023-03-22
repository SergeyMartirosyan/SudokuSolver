public class SudokuCell{
    private int value;
    private boolean fixed;

    public SudokuCell(int value, Boolean fixed){
        this.value = value;
        this.fixed = fixed;
    }

    public void setValue(int value){
		this.value = value;
	}

	public void setFixed(Boolean fixed){
		this.fixed = fixed;
	}

	public int getValue(){
		return value;
	}

	public Boolean getFixed(){
		return fixed;
    }
}