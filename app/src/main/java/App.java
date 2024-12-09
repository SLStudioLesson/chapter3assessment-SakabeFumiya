import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();

            if(choice.equals("1")){
                //「1」を選択した場合、CSVDataHandlerインスタンスを生成する
                DataHandler dataHandler = new CSVDataHandler();
            } else if(choice.equals("2")){
                //「2」を選択した場合、JSONDataHandlerインスタンスを生成する
                DataHandler dataHandler = new JSONDataHandler();
            }else{
                // 不正な入力（「1」「2」以外）が与えられた場合、CSVDataHandlerインスタンスを生成する
                DataHandler dataHandler = new CSVDataHandler();
            }

            // 選択されたデータハンドラーをcom/recipeappパッケージのRecipeUIに渡し、
            RecipeUI recipeUI = new RecipeUI(dataHandler);
            // displayMenuメソッドを呼び出してメインメニューを表示します。
            recipeUI.displayMenu();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}