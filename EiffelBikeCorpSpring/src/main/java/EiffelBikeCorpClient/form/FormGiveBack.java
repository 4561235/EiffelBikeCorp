package EiffelBikeCorpClient.form;

import javax.validation.constraints.NotNull;

public class FormGiveBack {

    @NotNull
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
