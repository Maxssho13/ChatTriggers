package com.chattriggers.ctjs.utils.config

import com.chattriggers.ctjs.minecraft.libs.MathLib
import com.chattriggers.ctjs.minecraft.libs.renderer.Rectangle
import com.chattriggers.ctjs.minecraft.libs.renderer.Renderer
import com.chattriggers.ctjs.minecraft.libs.renderer.Text
import com.chattriggers.ctjs.minecraft.wrappers.Client
import net.minecraft.client.gui.GuiButton
import java.awt.Color
import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty

open class ConfigColor
(private val prop: KMutableProperty<Color>, name: String = "", x: Int = 0, y: Int = 0)
    : ConfigOption() {

    private var value: Color by Delegates.observable(prop.getter.call(Config)) { _, _, new ->
        prop.setter.call(Config, new)
    }
    private val initial = value

    init {
        this.name = name

        this.x = x
        this.y = y
    }


    private var redButton = GuiButton(
            0,
            MathLib.map(this.value.red.toDouble(), 0.0, 255.0,
                    (Renderer.screen.getWidth() / 2 - 100 + this.x).toDouble(),
                    (Renderer.screen.getWidth() / 2 + 52 + this.x).toDouble()).toInt(),
            this.y + 15,
            5, 10, "")


    private var greenButton = GuiButton(0,
            MathLib.map(this.value.green.toDouble(), 0.0, 255.0, (Renderer.screen.getWidth() / 2 - 100 + this.x).toDouble(), (Renderer.screen.getWidth() / 2 + 52 + this.x).toDouble()).toInt(), this.y + 30,
            5, 10, "")


    private var blueButton = GuiButton(0,
            MathLib.map(this.value.blue.toDouble(), 0.0, 255.0, (Renderer.screen.getWidth() / 2 - 100 + this.x).toDouble(), (Renderer.screen.getWidth() / 2 + 52 + this.x).toDouble()).toInt(), this.y + 45,
            5, 10, "")

    private var redHeld: Boolean = false
    private var blueHeld: Boolean = false
    private var greenHeld: Boolean = false

    override fun draw(mouseX: Int, mouseY: Int, partialTicks: Float) {
        if (this.hidden) return

        val middle = Renderer.screen.getWidth() / 2

        Rectangle(-0x80000000, (middle - 105 + this.x).toDouble(), (this.y - 5).toDouble(), 210.0, 65.0)
                .setShadow(-0x30000000, 3.0, 3.0)
                .draw()
        Text(this.name!!, (middle - 100 + this.x).toDouble(), this.y.toDouble()).draw()

        // red slider
        Rectangle(-0x560000, (middle - 100 + this.x).toDouble(), (this.y + 19).toDouble(), 155.0, 3.0)
                .setOutline(-0x1000000, 1.0)
                .draw()

        //#if MC<=10809
        this.redButton.xPosition = MathLib.map(this.value.red.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
        this.redButton.drawButton(Client.getMinecraft(), mouseX, mouseY)
        //#else
        //$$ this.redButton.x = MathLib.map(this.value.red.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
        //$$ this.redButton.drawButton(Client.getMinecraft(), mouseX, mouseY, partialTicks)
        //#endif

        // green slider
        Rectangle(-0xff7800, (middle - 100 + this.x).toDouble(), (this.y + 34).toDouble(), 155.0, 3.0)
                .setOutline(-0x1000000, 1.0)
                .draw()

        //#if MC<=10809
        this.greenButton.xPosition = MathLib.map(this.value.green.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
        this.greenButton.drawButton(Client.getMinecraft(), mouseX, mouseY)
        //#else
        //$$ this.greenButton.x = MathLib.map(this.value.green.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
        //$$ this.greenButton.drawButton(Client.getMinecraft(), mouseX, mouseY, partialTicks)
        //#endif

        // blue slider
        Rectangle(-0xffff34, (middle - 100 + this.x).toDouble(), (this.y + 49).toDouble(), 155.0, 3.0)
                .setOutline(-0x1000000, 1.0)
                .draw()
        //#if MC<=10809
        this.blueButton.xPosition = MathLib.map(this.value.blue.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
        this.blueButton.drawButton(Client.getMinecraft(), mouseX, mouseY)
        //#else
        //$$ this.blueButton.x = MathLib.map(this.value.blue.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
        //$$ this.blueButton.drawButton(Client.getMinecraft(), mouseX, mouseY, partialTicks)
        //#endif

        // color preview
        Rectangle(this.value.rgb, (middle + this.x + 60).toDouble(), (this.y + 15).toDouble(), 40.0, 40.0)
                .setOutline(-0x1000000, 1.0)
                .draw()

        handleHeldButtons(mouseX, middle)

        super.draw(mouseX, mouseY, partialTicks)
    }

    private fun handleHeldButtons(mouseX: Int, middle: Int) {
        if (this.redHeld) {
            //#if MC<=10809
            this.redButton.xPosition = mouseX - 1
            //#else
            //$$ this.redButton.x = mouseX - 1
            //#endif

            limitHeldButton(this.redButton)
            this.value = Color(
                    MathLib.map(
                            //#if MC<=10809
                            this.redButton.xPosition.toDouble(),
                            //#else
                            //$$ this.redButton.x.toDouble(),
                            //#endif
                            (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble(), 0.0, 255.0
                    ).toInt(),
                    this.value.green,
                    this.value.blue
            )
        }
        if (this.greenHeld) {
            //#if MC<=10809
            this.greenButton.xPosition = mouseX - 1
            //#else
            //$$ this.greenButton.x = mouseX - 1
            //#endif

            limitHeldButton(this.greenButton)
            this.value = Color(
                    this.value.red,
                    MathLib.map(
                            //#if MC<=10809
                            this.greenButton.xPosition.toDouble(),
                            //#else
                            //$$ this.greenButton.x.toDouble(),
                            //#endif
                            (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble(), 0.0, 255.0
                    ).toInt(),
                    this.value.blue
            )
        }
        if (this.blueHeld) {
            //#if MC<=10809
            this.blueButton.xPosition = mouseX - 1
            //#else
            //$$ this.blueButton.x = mouseX - 1
            //#endif

            limitHeldButton(this.blueButton)
            this.value = Color(
                    this.value.red,
                    this.value.green,
                    MathLib.map(
                            //#if MC<=10809
                            this.blueButton.xPosition.toDouble(),
                            //#else
                            //$$ this.blueButton.x.toDouble(),
                            //#endif
                            (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble(), 0.0, 255.0
                    ).toInt()
            )
        }
    }

    private fun limitHeldButton(button: GuiButton) {
        //#if MC<=10809
        if (button.xPosition < Renderer.screen.getWidth() / 2 - 100 + this.x)
            button.xPosition = Renderer.screen.getWidth() / 2 - 100 + this.x
        if (button.xPosition > Renderer.screen.getWidth() / 2 + 52 + this.x)
            button.xPosition = Renderer.screen.getWidth() / 2 + 52 + this.x
        //#else
        //$$ if (button.x < Renderer.screen.getWidth() / 2 - 100 + this.x)
        //$$     button.x = Renderer.screen.getWidth() / 2 - 100 + this.x
        //$$ if (button.x > Renderer.screen.getWidth() / 2 + 52 + this.x)
        //$$     button.x = Renderer.screen.getWidth() / 2 + 52 + this.x
        //#endif
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int) {
        if (this.hidden) return

        if (this.redButton.mousePressed(Client.getMinecraft(), mouseX, mouseY)) {
            this.redHeld = true
            this.redButton.playPressSound(Client.getMinecraft().soundHandler)
        }
        if (this.greenButton.mousePressed(Client.getMinecraft(), mouseX, mouseY)) {
            this.greenHeld = true
            this.greenButton.playPressSound(Client.getMinecraft().soundHandler)
        }
        if (this.blueButton.mousePressed(Client.getMinecraft(), mouseX, mouseY)) {
            this.blueHeld = true
            this.blueButton.playPressSound(Client.getMinecraft().soundHandler)
        }

        if (this.resetButton.mousePressed(Client.getMinecraft(), mouseX, mouseY)) {
            this.value = this.initial
            val middle = Renderer.screen.getWidth() / 2
            //#if MC<=10809
            this.redButton.xPosition = MathLib.map(this.value.red.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
            this.greenButton.xPosition = MathLib.map(this.value.green.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
            this.blueButton.xPosition = MathLib.map(this.value.blue.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
            //#else
            //$$ this.redButton.x = MathLib.map(this.value.red.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
            //$$ this.greenButton.x = MathLib.map(this.value.green.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
            //$$ this.blueButton.x = MathLib.map(this.value.blue.toDouble(), 0.0, 255.0, (middle - 100 + this.x).toDouble(), (middle + 52 + this.x).toDouble()).toInt()
            //#endif
            this.resetButton.playPressSound(Client.getMinecraft().soundHandler)
        }
    }

    override fun mouseReleased() {
        this.redHeld = false
        this.blueHeld = false
        this.greenHeld = false
    }
}

class SpecialConfigColor
(prop: KMutableProperty<Color>, name: String = "", x: Int = 0, y: Int = 0)
    : ConfigColor(prop, name, x, y) {
    override fun draw(mouseX: Int, mouseY: Int, partialTicks: Float) {
        hidden = !Config.customTheme

        super.draw(mouseX, mouseY, partialTicks)
    }
}