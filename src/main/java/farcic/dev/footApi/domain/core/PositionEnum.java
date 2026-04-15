package farcic.dev.footApi.domain.core;

public enum PositionEnum {

    GOLEIRO("Goleiro", "GOL"),
    ZAGUEIRO("Zagueiro Central", "ZAG"),
    LATERAL_DIREITO("Lateral Direito", "LD"),
    LATERAL_ESQUERDO("Lateral Esquerdo", "LE"),
    VOLANTE("Volante", "VOL"),
    MEIA_CENTRAL("Meia Central", "MC"),
    MEIA_ATACANTE("Meia Atacante", "MAT"),
    PONTA_DIREITA("Ponta Direita", "PD"),
    PONTA_ESQUERDA("Ponta Esquerda", "PE"),
    CENTROAVANTE("Centroavante", "CA");


    private final String descricao;
    private final String sigla;

    PositionEnum(String descricao, String sigla) {
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSigla() {
        return sigla;
    }
}
