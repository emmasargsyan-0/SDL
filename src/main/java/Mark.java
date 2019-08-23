public class Mark {

    private int subjectId;
    private int mark;

    public Mark(int subjectId, int mark){
        this.mark = mark;
        this.subjectId = subjectId;
    }

    public int getMark() {
        return mark;
    }

    public int getSubjectId() {
        return subjectId;
    }
}
