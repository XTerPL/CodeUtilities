package io.github.codeutilities.websocket.client.type;

import com.google.gson.JsonObject;
import io.github.codeutilities.CodeUtilities;
import io.github.codeutilities.util.template.TemplateUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.io.IOException;

public abstract class AbstractTemplateItem extends SocketItem {

    @Override
    public ItemStack getItem(String data) throws Exception {
        JsonObject templateObject = CodeUtilities.JSON_PARSER.parse(data).getAsJsonObject();

        String author;
        Text name;
        String templateData = parseJsonData(templateObject.get("data").getAsString());

        ItemStack stack = new ItemStack(Items.ENDER_CHEST);
        if (templateObject.has("author")) {
            author = templateObject.get("author").getAsString();
        } else {
            author = MinecraftClient.getInstance().player.getGameProfile().getName();
        }
        if (templateObject.has("name")) {
            name = new LiteralText(templateObject.get("name").getAsString());
        } else {
            name = new LiteralText("Imported Code Template").styled((style) -> style.withColor(TextColor.fromRgb(11163135)));
        }
        int version;
        if (templateObject.has("version")) {
            version = templateObject.get("version").getAsInt();
        } else {
            version = TemplateUtil.VERSION;
        }

        stack.setCustomName(name);
        TemplateUtil.applyRawTemplateNBT(stack, (LiteralText) name, author, templateData, version);
        return stack;
    }

    public abstract String parseJsonData(String templateData) throws IOException;

}