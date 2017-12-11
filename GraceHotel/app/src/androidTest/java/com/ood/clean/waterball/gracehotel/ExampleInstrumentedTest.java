package com.ood.clean.waterball.gracehotel;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ood.clean.waterball.gracehotel.Model.datamodel.RadioGroupQuestion;
import com.ood.clean.waterball.gracehotel.Model.datamodel.FillingQuestion;
import com.ood.clean.waterball.gracehotel.Model.domain.QAModelFactory;
import com.ood.clean.waterball.gracehotel.Model.entity.Answer;
import com.ood.clean.waterball.gracehotel.Model.entity.Question;
import com.ood.clean.waterball.gracehotel.Model.entity.QuestionType;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public String questionTxt = "question";
    public String hintTxt = "綜合意見";
    public String[] options = new String[]{"滿意", "普通", "不滿意"};

    @Test
    public void testCheckboxXML() throws IOException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        AssetManager assetManager = appContext.getAssets();
        String xml = readInputStream(assetManager.open("test_chbx.xml"));
        Question question = new Question(1, questionTxt, xml, QuestionType.RADIOGROUP);
        RadioGroupQuestion questionModel = (RadioGroupQuestion) QAModelFactory.createQuestionModel(question);
        assertEquals(questionTxt, questionModel.getQuestion());
        for ( int i = 0 ; i < questionModel.getOptions().size() ; i ++ )
            assertEquals(options[i], questionModel.getOption(i).getOptionName());

        questionModel.getOption(0).setValue(true);
        questionModel.getOption(1).setValue(true);
        Answer answer = QAModelFactory.createAnswerFeedback("", "", "", questionModel);
        assertEquals("", answer.getDeviceUID());
        System.out.println("Checkboxes xml: " + answer.getResponses());
    }

    @Test
    public void testFillingXML() throws IOException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        AssetManager assetManager = appContext.getAssets();
        String xml = readInputStream(assetManager.open("test_filling.xml"));
        Question question = new Question(1, questionTxt, xml, QuestionType.FILLING);
        FillingQuestion questionModel = (FillingQuestion) QAModelFactory.createQuestionModel(question);
        assertEquals(questionTxt, questionModel.getQuestion());
        assertEquals(hintTxt, questionModel.getHint());

        questionModel.setAnswer("Hi, it's my answer.");
        Answer answer = QAModelFactory.createAnswerFeedback("", "", "", questionModel);
        assertEquals("", answer.getDeviceUID());
        System.out.println("Filling xml: " + answer.getResponses());
    }

    private String readInputStream(InputStream inputStream) throws IOException {
        ReadableByteChannel channel = Channels.newChannel(inputStream);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        WritableByteChannel outChannel = Channels.newChannel(bout);
        while (channel.read(byteBuffer) > 0 || byteBuffer.position() > 0) {
            byteBuffer.flip();  //make buffer ready for write
            outChannel.write(byteBuffer);
            byteBuffer.compact(); //make buffer ready for reading
        }
        channel.close();
        outChannel.close();
        return bout.toString("UTF-8");
    }
}
