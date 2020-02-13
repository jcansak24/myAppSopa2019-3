package com.example.myappsopa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Comenzar extends Activity {
    boolean band;
    int nivIni;
    Set<Integer> numUsados;
    Set<Integer> auxUsados;
    Set<Integer> alreadyUsedNumbers;
    String nomb;
    Bundle datos;
    int pos[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datos = getIntent().getExtras();
        nomb = datos.getString("nombre");
        numUsados = new HashSet<>();
        auxUsados = new HashSet<>();
        alreadyUsedNumbers = new HashSet<>();
        TextView name = (TextView)findViewById(R.id.nombreJugador);
        name.setText(""+nomb);
        llenarTab();
    }

    public String getNombre(){
        return nomb;
    }

    public void setNivel(int niv){
        this.nivIni = niv;
    }

    public int getNivel(){
        return nivIni;
    }

    public void setBand(boolean band){
        this.band = band;
    }

    public boolean getBand(){
        return band;
    }

    public void vertInvert(char matrizSopa[][], String pal, int f, int c, int tamIni) {
        int contSpace=0;
        for (int i = 0; i < pal.length(); i++) {
            if(f>=pal.length()-1) {
                if (matrizSopa[f - i][c] == ' ') contSpace++;
            }
        }
        if(contSpace==pal.length()) {
            for (int i = 0; i < pal.length(); i++) {
                matrizSopa[f - i][c] = pal.charAt(i);
            }
            setBand(true);
        }
    }

    public void diagArriNorm(char matrizSopa[][], String pal, int f, int c, int tamIni) {
        int contSpace=0;
        for (int i = 0; i < pal.length(); i++) {
            if(f>=pal.length()-1 && c<=tamIni-pal.length()) {
                if (matrizSopa[f - i][c + i] == ' ') contSpace++;
            }
        }
        if(contSpace==pal.length()) {
            for (int i = 0; i < pal.length(); i++) {
                matrizSopa[f - i][c + i] = pal.charAt(i);
            }
            setBand(true);
        }
    }

    public void horizNorm(char matrizSopa[][], String pal, int f, int c, int tamIni) {
        int contSpace=0;
        for (int i = 0; i < pal.length(); i++) {
            if(c<=tamIni-pal.length()) {
                if (matrizSopa[f][c + i] == ' ') contSpace++;
            }
        }
        if(contSpace==pal.length()) {
            for (int i = 0; i < pal.length(); i++) {
                matrizSopa[f][c + i] = pal.charAt(i);
            }
            setBand(true);
        }
    }

    public void diagAbaNorm(char matrizSopa[][], String pal, int f, int c, int tamIni) {
        int contSpace=0;
        for (int i = 0; i < pal.length(); i++) {
            if(f<=tamIni-pal.length() && c<=tamIni-pal.length()) {
                if (matrizSopa[f + i][c + i] == ' ') contSpace++;
            }
        }
        if(contSpace==pal.length()) {
            for (int i = 0; i < pal.length(); i++) {
                matrizSopa[f + i][c + i] = pal.charAt(i);
            }
            setBand(true);
        }
    }

    public void vertNorm(char matrizSopa[][], String pal, int f, int c, int tamIni) {
        int contSpace=0;
        for (int i = 0; i < pal.length(); i++) {
            if(f<=tamIni-pal.length()) {
                if (matrizSopa[f + i][c] == ' ') contSpace++;
            }
        }
        if(contSpace==pal.length()) {
            for (int i = 0; i < pal.length(); i++) {
                matrizSopa[f + i][c] = pal.charAt(i);
            }
            setBand(true);
        }
    }

    public void diagAbaInvert(char matrizSopa[][], String pal, int f, int c, int tamIni) {
        int contSpace=0;
        for (int i = 0; i < pal.length(); i++) {
            if(c>=pal.length()-1 && f<=tamIni-pal.length()) {
                if (matrizSopa[f + i][c - i] == ' ') contSpace++;
            }
        }
        if(contSpace==pal.length()) {
            for (int i = 0; i < pal.length(); i++) {
                matrizSopa[f + i][c - i] = pal.charAt(i);
            }
            setBand(true);
        }
    }

    public void horizInvert(char matrizSopa[][], String pal, int f, int c, int tamIni) {
        int contSpace=0;
        for (int i = 0; i < pal.length(); i++) {
            if(c>=pal.length()-1) {
                if (matrizSopa[f][c - i] == ' ') contSpace++;
            }
        }
        if(contSpace==pal.length()) {
            for (int i = 0; i < pal.length(); i++) {
                matrizSopa[f][c - i] = pal.charAt(i);
            }
            setBand(true);
        }
    }

    public void diagArriInvert(char matrizSopa[][], String pal, int f, int c, int tamIni) {
        int contSpace=0;
        for (int i = 0; i < pal.length(); i++) {
            if(f>=pal.length()-1 && c>=pal.length()-1) {
                if (matrizSopa[f - i][c - i] == ' ') contSpace++;
            }
        }
        if(contSpace==pal.length()) {
            for (int i = 0; i < pal.length(); i++) {
                matrizSopa[f - i][c - i] = pal.charAt(i);
            }
            setBand(true);
        }
    }

    public void llenarTab() {
        int tamIni = 7+numUsados.size();
        nivIni = 1+numUsados.size();
        setNivel(nivIni);
        TextView lvl = (TextView)findViewById(R.id.textView15);
        lvl.setText(nivIni);
        band=false;
        Button matrizBotones[][] = new Button[tamIni][tamIni];
        char matrizSopa[][] = new char[tamIni][tamIni];

        for (int i = 0; i < matrizSopa.length; i++) {
            for (int j = 0; j < matrizSopa[i].length; j++) {
                matrizSopa[i][j] = ' ';
                matrizBotones[i][j] = new Button(null);
            }
        }
        Random random = new Random();
        int r=0;

        while (numUsados.size() < 6) {
            r = random.nextInt(6) + 1;
            if (!numUsados.contains(r)) {
                if (r == 1) {
                    setContentView(R.layout.animales);
                }
                if (r == 2) {
                    setContentView(R.layout.alimentos);
                }
                if (r == 3) {
                    setContentView(R.layout.cuerpo);
                }
                if (r == 4) {
                    setContentView(R.layout.frutas);
                }
                if (r == 5) {
                    setContentView(R.layout.monedas);
                }
                if (r == 6) {
                    setContentView(R.layout.paises);
                }
                numUsados.add(r);
            }
        }

        pos = new int[12*12];

        pos[0]=R.id.button0; pos[1]=R.id.button1; pos[2]=R.id.button2; pos[3]=R.id.button3; pos[4]=R.id.button4; pos[5]=R.id.button5;
        pos[6]=R.id.button6; pos[7]=R.id.button7; pos[8]=R.id.button8; pos[9]=R.id.button9; pos[10]=R.id.button10; pos[11]=R.id.button11;
        pos[12]=R.id.button12; pos[13]=R.id.button13; pos[14]=R.id.button14; pos[15]=R.id.button15; pos[16]=R.id.button16; pos[17]=R.id.button17;
        pos[18]=R.id.button18; pos[19]=R.id.button19; pos[20]=R.id.button20; pos[21]=R.id.button21; pos[22]=R.id.button22; pos[23]=R.id.button23;
        pos[24]=R.id.button24; pos[25]=R.id.button125; pos[26]=R.id.button26; pos[27]=R.id.button27; pos[28]=R.id.button28; pos[29]=R.id.button29;
        pos[30]=R.id.button30; pos[31]=R.id.button31; pos[32]=R.id.button32; pos[33]=R.id.button33; pos[34]=R.id.button34; pos[35]=R.id.button35;
        pos[36]=R.id.button36; pos[37]=R.id.button37; pos[38]=R.id.button38; pos[39]=R.id.button39; pos[40]=R.id.button40; pos[41]=R.id.button41;
        pos[42]=R.id.button42; pos[43]=R.id.button43; pos[44]=R.id.button44; pos[45]=R.id.button45; pos[46]=R.id.button46; pos[47]=R.id.button47;
        pos[48]=R.id.button48; pos[49]=R.id.button49; pos[50]=R.id.button50; pos[51]=R.id.button51; pos[52]=R.id.button52; pos[53]=R.id.button53;
        pos[54]=R.id.button54; pos[55]=R.id.button55; pos[56]=R.id.button56; pos[57]=R.id.button57; pos[58]=R.id.button58; pos[59]=R.id.button59;
        pos[60]=R.id.button60; pos[61]=R.id.button61; pos[62]=R.id.button62; pos[63]=R.id.button63; pos[64]=R.id.button64; pos[65]=R.id.button65;
        pos[66]=R.id.button66; pos[67]=R.id.button67; pos[68]=R.id.button68; pos[69]=R.id.button69; pos[70]=R.id.button70; pos[71]=R.id.button71;
        pos[72]=R.id.button72; pos[73]=R.id.button73; pos[74]=R.id.button74; pos[75]=R.id.button75; pos[76]=R.id.button76; pos[77]=R.id.button77;
        pos[78]=R.id.button78; pos[79]=R.id.button79; pos[80]=R.id.button80; pos[81]=R.id.button81; pos[82]=R.id.button82; pos[83]=R.id.button83;
        pos[84]=R.id.button84; pos[85]=R.id.button85; pos[86]=R.id.button86; pos[87]=R.id.button87; pos[88]=R.id.button88; pos[89]=R.id.button89;
        pos[90]=R.id.button90; pos[91]=R.id.button91; pos[92]=R.id.button92; pos[93]=R.id.button93; pos[94]=R.id.button94; pos[95]=R.id.button95;
        pos[96]=R.id.button96; pos[97]=R.id.button97; pos[98]=R.id.button98; pos[99]=R.id.button99; pos[100]=R.id.button100; pos[101]=R.id.button101;
        pos[102]=R.id.button102; pos[103]=R.id.button103; pos[104]=R.id.button104; pos[105]=R.id.button105; pos[106]=R.id.button106; pos[107]=R.id.button107;
        pos[108]=R.id.button108; pos[109]=R.id.button109; pos[110]=R.id.button110; pos[111]=R.id.button111; pos[112]=R.id.button112; pos[113]=R.id.button113;
        pos[114]=R.id.button114; pos[115]=R.id.button115; pos[116]=R.id.button116; pos[117]=R.id.button117; pos[118]=R.id.button118; pos[119]=R.id.button119;
        pos[120]=R.id.button120; pos[121]=R.id.button121; pos[122]=R.id.button122; pos[123]=R.id.button123; pos[124]=R.id.button124; pos[125]=R.id.button125;
        pos[126]=R.id.button126; pos[127]=R.id.button127; pos[128]=R.id.button128; pos[129]=R.id.button129; pos[130]=R.id.button130; pos[131]=R.id.button131;
        pos[132]=R.id.button132; pos[133]=R.id.button133; pos[134]=R.id.button134; pos[135]=R.id.button135; pos[136]=R.id.button136; pos[137]=R.id.button137;
        pos[138]=R.id.button138; pos[139]=R.id.button139; pos[140]=R.id.button140; pos[141]=R.id.button141; pos[142]=R.id.button142; pos[143]=R.id.button143;

        String vecAnimales[] = {"MOSCA", "GRILLO", "SALTAMONTES", "MARIPOSA", "ESCARABAJO", "ABEJA", "GATO", "MARIQUITA",
                "PERRO", "IGUANA", "PINGUINO", "OVEJA", "CONEJO", "CABALLO", "ELEFANTE", "PAJARO", "GORILA",
                "HORMIGA", "OCELOTE", "BISONTE", "CAMELLO", "BOGAVANTE", "ERIZO", "CANGURO", "MAPACHE"};
        String vecAlimentos[] = {"ARROZ", "PASTA", "CARAOTAS", "MAIZ", "AZUCAR", "SAL", "HARINA", "LECHE", "QUESO", "POLLO",
                "CARNE", "PAN", "TRIGO", "CEBADA", "ZANAHORIA", "RABANO", "CILANTRO", "LENTEJAS", "HUEVOS",
                "PESCADO", "CAFE", "GALLETAS", "NUEZ", "ACEITUNA", "SOPA"};
        String vecCuerpo[] = {"OJOS", "NARIZ", "BOCA", "LENGUA", "DIENTES", "OREJAS", "PARPADOS", "ENCIAS", "LARINJE", "GARGANTA",
                "PULMONES", "CORAZON", "ESTOMAGO", "INTESTINOS", "MEDULA", "COLUMNA", "COSTILLAS", "HIGADO", "BAZO",
                "APENDICE", "AMIGDALA", "HUESO", "VENAS", "ARTERIAS", "SANGRE"};
        String vecFrutas[] = {"ARANDANO", "CIRUELA", "HIGO", "KIWI", "NARANJA", "CEREZA", "FRESA", "SANDIA", "MANDARINA", "MORA",
                "ALBARICOQUE", "DURAZNO", "NISPERO", "ZAPOTE", "BANANA", "LIMON", "CURUBA", "TORONJA", "GRANADILLA",
                "GUAYABA", "GUANABANA", "MANGO", "MANZANA", "PERA", "UVA"};
        String vecMonedas[] = {"DOLAR", "EURO", "DINAR", "RUBLO", "YUAN", "YEN", "LIRA", "LIBRA", "PESO", "BOLIVAR", "REAL", "CORONA", "TENGE",
                "FRANCO", "SOL", "DIRHAM", "RUPIA", "CORDOBA", "GUARANI", "LEMPIRA", "COLON", "QUETZAL", "FLORIN", "MANAT", "WON"};
        String vecPaises[] = {"ALEMANIA", "AUSTRALIA", "AUSTRIA", "CANADA", "BRASIL", "JAPON", "CHINA", "LUXEMBURGO", "LATVIA", "BANGLADESH",
                "SEYCHELLES", "ESLOVAKIA", "AZERBAIJAN", "MAURITANIA", "MADAGASCAR", "VENEZUELA", "INGLATERRA", "ARGENTINA", "KOREA",
                "SUIZA", "FRANCIA", "KOSOVO", "KIRIBATI", "URUGUAY", "SERBIA"};

        //int vertInv = 1, diagArriNorm = 2, horizNorm = 3, diagAbaNorm = 4, vertNorm = 5, diagAabaInv = 6, horizInv = 7, diagArriInv = 8;

        /*int auxAleat = 0;
        int aleat = random.nextInt(tamIni - 1) + 1;
        do {
            if (auxAleat == aleat) aleat = random.nextInt(tamIni - 1) + 1;
        } while (aleat == auxAleat);*/
        int cantPalabras = 0, palIni=6, c;
        char caracter;
        String vecPal[];
        switch (nivIni) {
            case 1:
                vecPal = new String[palIni];
                while(cantPalabras==palIni) {
                    if(r==1) {
                        while (alreadyUsedNumbers.size() < vecAnimales.length) {
                            int randomNumber = random.nextInt(vecAnimales.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);

                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==2) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecAlimentos.length) {
                            int randomNumber = random.nextInt(vecAlimentos.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==3) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecCuerpo.length) {
                            int randomNumber = random.nextInt(vecCuerpo.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==4) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecFrutas.length) {
                            int randomNumber = random.nextInt(vecFrutas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }

                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==5) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecMonedas.length) {
                            int randomNumber = random.nextInt(vecMonedas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==6) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecPaises.length) {
                            int randomNumber = random.nextInt(vecPaises.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                }
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        if(matrizSopa[i][j] == ' '){
                            c = random.nextInt(25)+65;
                            caracter = (char)c;
                            matrizSopa[i][j] = caracter;
                        }
                    }
                }
                int x=0;
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        matrizBotones[i][j] = (Button) findViewById(pos[x]);
                        matrizBotones[i][j].setText(matrizSopa[i][j]);
                        x++;
                    }
                    x+=5;
                }
                break;
            case 2:
                palIni=8;
                alreadyUsedNumbers.clear();
                vecPal = new String[palIni];
                while(cantPalabras==palIni) {
                    if(r==1) {
                        while (alreadyUsedNumbers.size() < vecAnimales.length) {
                            int randomNumber = random.nextInt(vecAnimales.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);

                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==2) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecAlimentos.length) {
                            int randomNumber = random.nextInt(vecAlimentos.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==3) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecCuerpo.length) {
                            int randomNumber = random.nextInt(vecCuerpo.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==4) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecFrutas.length) {
                            int randomNumber = random.nextInt(vecFrutas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }

                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==5) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecMonedas.length) {
                            int randomNumber = random.nextInt(vecMonedas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==6) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecPaises.length) {
                            int randomNumber = random.nextInt(vecPaises.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                }
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        if(matrizSopa[i][j] == ' '){
                            c = random.nextInt(25)+65;
                            caracter = (char)c;
                            matrizSopa[i][j] = caracter;
                        }
                    }
                }
                x=0;
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        matrizBotones[i][j] = (Button) findViewById(pos[x]);
                        matrizBotones[i][j].setText(matrizSopa[i][j]);
                        x++;
                    }
                    x+=4;
                }
                break;
            case 3:
                palIni=10;
                alreadyUsedNumbers.clear();
                vecPal = new String[palIni];
                while(cantPalabras==palIni) {
                    if(r==1) {
                        while (alreadyUsedNumbers.size() < vecAnimales.length) {
                            int randomNumber = random.nextInt(vecAnimales.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);

                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==2) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecAlimentos.length) {
                            int randomNumber = random.nextInt(vecAlimentos.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==3) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecCuerpo.length) {
                            int randomNumber = random.nextInt(vecCuerpo.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==4) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecFrutas.length) {
                            int randomNumber = random.nextInt(vecFrutas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }

                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==5) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecMonedas.length) {
                            int randomNumber = random.nextInt(vecMonedas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==6) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecPaises.length) {
                            int randomNumber = random.nextInt(vecPaises.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                }
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        if(matrizSopa[i][j] == ' '){
                            c = random.nextInt(25)+65;
                            caracter = (char)c;
                            matrizSopa[i][j] = caracter;
                        }
                    }
                }
                x=0;
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        matrizBotones[i][j] = (Button) findViewById(pos[x]);
                        matrizBotones[i][j].setText(matrizSopa[i][j]);
                        x++;
                    }
                    x+=3;
                }
                break;
            case 4:
                palIni=12;
                alreadyUsedNumbers.clear();
                vecPal = new String[palIni];
                while(cantPalabras==palIni) {
                    if(r==1) {
                        while (alreadyUsedNumbers.size() < vecAnimales.length) {
                            int randomNumber = random.nextInt(vecAnimales.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);

                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==2) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecAlimentos.length) {
                            int randomNumber = random.nextInt(vecAlimentos.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==3) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecCuerpo.length) {
                            int randomNumber = random.nextInt(vecCuerpo.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==4) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecFrutas.length) {
                            int randomNumber = random.nextInt(vecFrutas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }

                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==5) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecMonedas.length) {
                            int randomNumber = random.nextInt(vecMonedas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==6) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecPaises.length) {
                            int randomNumber = random.nextInt(vecPaises.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                }
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        if(matrizSopa[i][j] == ' '){
                            c = random.nextInt(25)+65;
                            caracter = (char)c;
                            matrizSopa[i][j] = caracter;
                        }
                    }
                }
                x=0;
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        matrizBotones[i][j] = (Button) findViewById(pos[x]);
                        matrizBotones[i][j].setText(matrizSopa[i][j]);
                        x++;
                    }
                    x+=2;
                }
                break;
            case 5:
                palIni=14;
                alreadyUsedNumbers.clear();
                vecPal = new String[palIni];
                while(cantPalabras==palIni) {
                    if(r==1) {
                        while (alreadyUsedNumbers.size() < vecAnimales.length) {
                            int randomNumber = random.nextInt(vecAnimales.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);

                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==2) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecAlimentos.length) {
                            int randomNumber = random.nextInt(vecAlimentos.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==3) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecCuerpo.length) {
                            int randomNumber = random.nextInt(vecCuerpo.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==4) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecFrutas.length) {
                            int randomNumber = random.nextInt(vecFrutas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }

                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==5) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecMonedas.length) {
                            int randomNumber = random.nextInt(vecMonedas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==6) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecPaises.length) {
                            int randomNumber = random.nextInt(vecPaises.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                }
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        if(matrizSopa[i][j] == ' '){
                            c = random.nextInt(25)+65;
                            caracter = (char)c;
                            matrizSopa[i][j] = caracter;
                        }
                    }
                }
                x=0;
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        matrizBotones[i][j] = (Button) findViewById(pos[x]);
                        matrizBotones[i][j].setText(matrizSopa[i][j]);
                        x++;
                    }
                    x++;
                }
                break;
            case 6:
                palIni=16;
                alreadyUsedNumbers.clear();
                vecPal = new String[palIni];
                while(cantPalabras==palIni) {
                    if(r==1) {
                        while (alreadyUsedNumbers.size() < vecAnimales.length) {
                            int randomNumber = random.nextInt(vecAnimales.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAnimales[randomNumber], fila, col, tamIni);

                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==2) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecAlimentos.length) {
                            int randomNumber = random.nextInt(vecAlimentos.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecAlimentos[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==3) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecCuerpo.length) {
                            int randomNumber = random.nextInt(vecCuerpo.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecCuerpo[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==4) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecFrutas.length) {
                            int randomNumber = random.nextInt(vecFrutas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecFrutas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }

                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==5) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecMonedas.length) {
                            int randomNumber = random.nextInt(vecMonedas.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecMonedas[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                    if(r==6) {
                        vecPal = new String[palIni];
                        while (alreadyUsedNumbers.size() < vecPaises.length) {
                            int randomNumber = random.nextInt(vecPaises.length);
                            int fila = random.nextInt(tamIni);
                            int col = random.nextInt(tamIni);
                            if (!alreadyUsedNumbers.contains(randomNumber)) {
                                if (getBand() == false)
                                    vertInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    vertNorm(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagAbaInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    horizInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == false)
                                    diagArriInvert(matrizSopa, vecPaises[randomNumber], fila, col, tamIni);
                                if (getBand() == true) {
                                    auxUsados.add(randomNumber);
                                    vecPal[cantPalabras] = vecAnimales[randomNumber];
                                    cantPalabras++;
                                }
                                alreadyUsedNumbers.add(randomNumber);
                            }
                        }
                        if(cantPalabras<palIni){
                            alreadyUsedNumbers.clear();
                            alreadyUsedNumbers.addAll(auxUsados);
                        }
                    }
                }
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        if(matrizSopa[i][j] == ' '){
                            c = random.nextInt(25)+65;
                            caracter = (char)c;
                            matrizSopa[i][j] = caracter;
                        }
                    }
                }
                x=0;
                for (int i = 0; i < matrizSopa.length; i++) {
                    for (int j = 0; j < matrizSopa[i].length; j++) {
                        matrizBotones[i][j] = (Button) findViewById(pos[x]);
                        matrizBotones[i][j].setText(matrizSopa[i][j]);
                        x++;
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mimenu) {
        getMenuInflater().inflate(R.menu.menu, mimenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem optionMenu) {
        MenuJuego mj = new MenuJuego();
        RegistroUser ru = new RegistroUser();
        int id = optionMenu.getItemId();
        if (id == R.id.configuracion) {
            return true;
        }
        if (id == R.id.creditos) {
            mj.ejecutarCreditos(null);
            return true;
        }
        if (id == R.id.salirJuego) {
            ru.ejecutarMenu(null);
            return true;
        }
        return super.onOptionsItemSelected(optionMenu);
    }
}
