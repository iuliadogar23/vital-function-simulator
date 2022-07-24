package lucrare.dizertatie.simulatorfunctiivitale.util;

public class Pacient {

    private Integer id;

    private static Pacient pacientInstance = null;

    private Pacient(Integer id) {
        this.id = id;
    }

    public static Pacient getInstance(Integer id) {
        if (pacientInstance == null)
            pacientInstance = new Pacient(id);
        return pacientInstance;
    }

    public Integer getId() {
        return id;
    }

    public static String addPacientToNewsReport(String newsReport) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n")
                .append("pacientId : "+pacientInstance.getId())
                .append("\n")
                .append(newsReport);
        return stringBuilder.toString();
    }

}
