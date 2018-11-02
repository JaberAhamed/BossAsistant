package com.example.jahed.bossassistant;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;


/**
 * Created by User on 7/11/2018.
 */

public class AppEmailDataAdapter extends RecyclerView.Adapter<AppEmailDataAdapter.MovieViewHolder> {



    private Context context;

    private ArrayList<EmailClass>itemList;
    boolean isExecuted = false;
    ToggolButtonHandleDataManager toggolButtonHandleDataManager ;
    ArrayList<HandletoggolButton> handletoggolButtons ;
    SetMessgeEmalitTextTime setMessgeEmalitTextTime;

    public AppEmailDataAdapter(Context context, ArrayList<EmailClass> messageClasses) {
        this.context = context;
        this.itemList = messageClasses;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.single_layout,parent,false);

        MovieViewHolder movieViewHolder = new MovieViewHolder(v);
        return movieViewHolder;


    }



    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

            setMessgeEmalitTextTime=new SetMessgeEmalitTextTime(context);
            holder.textType.setText((itemList.get(position)).getEmailName());
            holder.textMgsEm.setText(( itemList.get(position)).getEmailText());
           int month=itemList.get(position).getEmMonth()+1;
            holder.textDate.setText(itemList.get(position).getEmYear()+"/"+month+"/"+itemList.get(position).getEmDayOfMonth());
            String timeText=setMessgeEmalitTextTime.setTimeText(itemList.get(position).getEmHoure(),itemList.get(position).getEmMinute());
            holder.textTime.setText(timeText);




        holder.prentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(context, FileSetActivity.class);
                    intent.putExtra("key","EML");
                    intent.putExtra("pos",position);
                    context.startActivity(intent);

            }
        });


        holder.prentLayout.setOnLongClickListener(new View.OnLongClickListener() {

            EmailDataManager emailDataManager=new EmailDataManager(context);


            @Override
            public boolean onLongClick(View view) {

                if (holder.toggleButton.isChecked()){
                    Toast.makeText(context, "On cant't delete ", Toast.LENGTH_SHORT).show();
                }
                else {

                        emailDataManager.deleteEmail((itemList.get(position)).getId());
                        itemList.remove(position);

                        ArrayList<HandletoggolButton>handletoggolButtons=toggolButtonHandleDataManager.getAllMgsEmlToggolPosition("eml");
                        for (int i=0;i<handletoggolButtons.size();i++){

                            if (position<handletoggolButtons.get(i).getPositon()){

                                toggolButtonHandleDataManager.updateToggolePosition(new HandletoggolButton(handletoggolButtons.get(i).getId(),position,"eml"));
                            }
                        }

                        notifyDataSetChanged();
                        Toast.makeText(context, "can delete "+position, Toast.LENGTH_SHORT).show();




                }



                return true;

            }
        });

        handletoggolButtons = new ArrayList<>();
        ArrayList<HandletoggolButton>handletoggolButtons1=new ArrayList<>();
        toggolButtonHandleDataManager = new ToggolButtonHandleDataManager(context);
        handletoggolButtons = toggolButtonHandleDataManager.getAllMgsEmlToggolPosition("eml");

        Toast.makeText(context, " eml -- "+handletoggolButtons.size(), Toast.LENGTH_SHORT).show();
        for (int i=0;i<handletoggolButtons.size();i++){


            if (handletoggolButtons.get(i).getTexEmlName().equals("eml")){
                handletoggolButtons1.add(handletoggolButtons.get(i));

            }
        }

        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                int a=position+201;
                if (isChecked){

                    insertToggolButtonPosition(position);


                    setMessgeEmalitTextTime.setTextTime( a,position,itemList.get(position).getEmYear(),itemList.get(position).getEmMonth(),itemList.get(position).getEmDayOfMonth(),
                            itemList.get(position).getEmHoure(),itemList.get(position).getEmMinute(),itemList.get(position).getEmailText() );
                }
                else {
                    toggolButtonHandleDataManager.deleteToggolButtonPositon(position);
                    setMessgeEmalitTextTime.cancelAlarm(a);

                }
            }
        });








             toggolButtonHandleDataManager = new ToggolButtonHandleDataManager(context);

             if (!isExecuted) {


                 for (int i = 0; i < handletoggolButtons1.size(); i++) {

                     holder.toggleButton.setTag(itemList.get(handletoggolButtons1.get(i).getPositon()));
                     EmailClass emailClass1 = (EmailClass) holder.toggleButton.getTag();
                     emailClass1.setSelected(true);

                 }
                 isExecuted = true;
             }

             holder.toggleButton.setChecked((itemList.get(position)).getSelected());















    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private TextView textType,textMgsEm,textDate,textTime;

        private ToggleButton toggleButton;

        LinearLayout prentLayout;

        public MovieViewHolder(View itemView) {
            super(itemView);

            textType = (TextView) itemView.findViewById(R.id.textTypeTV);
            textMgsEm = (TextView) itemView.findViewById(R.id.textTV);
            textDate = (TextView) itemView.findViewById(R.id.dateTV);
            textTime = (TextView) itemView.findViewById(R.id.timeTV);
            toggleButton=(ToggleButton) itemView.findViewById(R.id.setTextTime);
            prentLayout=itemView.findViewById(R.id.parenLayout);

        }
    }

    private void insertToggolButtonPosition(int position) {

        ArrayList<Integer> kdf = new ArrayList<>();
        for (int i = 0; i < handletoggolButtons.size(); i++) {
            kdf.add(handletoggolButtons.get(i).getPositon());


        }
        if (!kdf.toString().contains(String.valueOf(position))) {

            toggolButtonHandleDataManager.addToggolButtonPositon(new HandletoggolButton(position,"eml"));
            Toast.makeText(context, "inserted"+position, Toast.LENGTH_SHORT).show();
        }

    }
}



    /*private Context context;
    private List<Object> itemList;
    private final int MGS = 0, EML = 1;
    MessageDataManager messageDataManager;

    boolean isExecuted = false;
    ToggolButtonHandleDataManager toggolButtonHandleDataManager = new ToggolButtonHandleDataManager(context);
    ArrayList<HandletoggolButton> handletoggolButtons = new ArrayList<>();

    public AppDataAdapter(Context context,List<Object>objects)
    {
        this.context=context;
        this.itemList=objects;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        if (viewType==MGS){

            View v1 = inflater.inflate(R.layout.single_layout, parent, false);
            viewHolder = new MessageViewwHolder(v1);
            return viewHolder;
        }
        else {
            View v2 = inflater.inflate(R.layout.single_layout, parent, false);
            viewHolder = new EmailViewHolder(v2);
            return viewHolder;

        }



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ArrayList<HandletoggolButton> handletoggolButtons = new ArrayList<>();
        switch (holder.getItemViewType()) {
            case MGS:
                MessageViewwHolder mgs = (MessageViewwHolder) holder;
                configureMgsViewHolder(mgs, position);

                break;
            case EML:
                EmailViewHolder eml = (EmailViewHolder) holder;
                configureEmlViewHolder2(eml, position);

        }





    }



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof MessageClass) {

            return MGS;
        } else if (itemList.get(position) instanceof EmailClass) {
            return EML;
        }
        return -1;
    }

public class MessageViewwHolder extends RecyclerView.ViewHolder
{

    private TextView textType,textMgsEm,textDate,textTime;

    private ToggleButton toggleButton;

    LinearLayout prentLayout;

    public MessageViewwHolder(View v) {
        super(v);
        textType = (TextView) v.findViewById(R.id.textTypeTV);
        textMgsEm = (TextView) v.findViewById(R.id.textTV);
        textDate = (TextView) v.findViewById(R.id.dateTV);
        textTime = (TextView) v.findViewById(R.id.timeTV);
        toggleButton=(ToggleButton) v.findViewById(R.id.setTextTime);
        prentLayout=v.findViewById(R.id.parenLayout);




    }

    public TextView getTextType() {
        return textType;
    }

    public void setTextType(TextView textType) {
        this.textType = textType;
    }

    public TextView getTextMgsEm() {
        return textMgsEm;
    }

    public void setTextMgsEm(TextView textMgsEm) {
        this.textMgsEm = textMgsEm;
    }

    public TextView getTextDate() {
        return textDate;
    }

    public void setTextDate(TextView textDate) {
        this.textDate = textDate;
    }

    public TextView getTextTime() {
        return textTime;
    }

    public void setTextTime(TextView textTime) {
        this.textTime = textTime;
    }
}
public class EmailViewHolder extends RecyclerView.ViewHolder
{

    TextView textType,textMgsEm,textDate,textTime;
    ToggleButton toggleButtonEml;
    LinearLayout prentLayout;

    public EmailViewHolder(View itemView) {
        super(itemView);
        textType=itemView.findViewById(R.id.textTypeTV);
        textMgsEm=itemView.findViewById(R.id.textTV);
        textDate=itemView.findViewById(R.id.dateTV);
        textTime=itemView.findViewById(R.id.timeTV);
        toggleButtonEml=(ToggleButton) itemView.findViewById(R.id.setTextTime);
        prentLayout=itemView.findViewById(R.id.parenLayout);
    }



}

    private void configureEmlViewHolder2(final EmailViewHolder eml, final int position) {

        EmailClass emailClass = (EmailClass) itemList.get(position);
        final EmailDataManager emailDataManager=new EmailDataManager(context);
        if (emailClass != null) {
            eml.textType.setText(emailClass.getEmailName());
            eml.textMgsEm.setText(emailClass.getEmailText());
            eml.textDate.setText(emailClass.getDate());
            eml.textTime.setText(emailClass.getHour()+":"+emailClass.getMinute()+" "+emailClass.getFormate());


            eml.prentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, ((EmailClass) itemList.get(position)).getEmailName()+((EmailClass) itemList.get(position)).getEmailText()+position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, FileSetActivity.class);
                    intent.putExtra("key","EML");
                    intent.putExtra("pos",((EmailClass) itemList.get(position)).getId());
                    context.startActivity(intent);
                }
            });

            eml.prentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if (eml.toggleButtonEml.isChecked()){
                        Toast.makeText(context, "On cant't delete ", Toast.LENGTH_SHORT).show();
                    }
                    else {



                        emailDataManager.deleteEmail(((EmailClass) itemList.get(position)).getId());
                        itemList.remove(position);

                        ArrayList<HandletoggolButton>handletoggolButtons=toggolButtonHandleDataManager.getAllMgsEmlToggolPosition();
                        for (int i=0;i<handletoggolButtons.size();i++){

                            if (position<handletoggolButtons.get(i).getPositon()){

                                toggolButtonHandleDataManager.updateToggolePosition(new HandletoggolButton(handletoggolButtons.get(i).getId(),position));
                            }
                        }

                        notifyDataSetChanged();
                        Toast.makeText(context, "can delete "+position, Toast.LENGTH_SHORT).show();
                    }



                    return true;



                }
            });

            eml.toggleButtonEml.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked){

                        insertToggolButtonPosition(position);

                    }
                    else {
                        toggolButtonHandleDataManager.deleteToggolButtonPositon(position);
                        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        *//*    if (!isExecuted) {
                handletoggolButtons = toggolButtonHandleDataManager.getAllMgsEmlToggolPosition();

                for (int i = 0; i < handletoggolButtons.size(); i++) {

                    eml.toggleButtonEml.setTag(itemList.get(handletoggolButtons.get(i).getPositon()));
                    EmailClass emailClass1 = (EmailClass) eml.toggleButtonEml.getTag();
                    emailClass1.setSelected(true);
                    Toast.makeText(context, " ....." + handletoggolButtons.size(), Toast.LENGTH_SHORT).show();
                }
                isExecuted = true;
            }
*//*
            //  setTegToggle(eml);

        }
        eml.toggleButtonEml.setChecked(((EmailClass) itemList.get(position)).getSelected());

    }

    private void configureMgsViewHolder(final MessageViewwHolder mgs, final int position) {
        MessageClass messageClass = (MessageClass) itemList.get(position);
        toggolButtonHandleDataManager = new ToggolButtonHandleDataManager(context);
        messageDataManager=new MessageDataManager(context);
        if (messageClass != null) {


            mgs.getTextType().setText(messageClass.getMessage());
            mgs.getTextMgsEm().setText(messageClass.getText());
            mgs.getTextDate().setText(messageClass.getDate());
            mgs.getTextTime().setText(messageClass.getHour()+":"+messageClass.getMinute()+" "+messageClass.getFormate());


            mgs.prentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, ((MessageClass) itemList.get(position)).getMessage()+((MessageClass) itemList.get(position)).getText()+position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, FileSetActivity.class);
                    intent.putExtra("key","MGS");
                    intent.putExtra("pos",position);
                    context.startActivity(intent);
                }
            });

            mgs.prentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mgs.toggleButton.isChecked()){
                        Toast.makeText(context, "On cant't delete ", Toast.LENGTH_SHORT).show();
                    }
                    else {


                        messageDataManager.deleteMessage(((MessageClass) itemList.get(position)).getId());
                        itemList.remove(position);

                        ArrayList<HandletoggolButton>handletoggolButtons=toggolButtonHandleDataManager.getAllMgsEmlToggolPosition();
                        for (int i=0;i<handletoggolButtons.size();i++){

                            if (position<handletoggolButtons.get(i).getPositon()){

                                toggolButtonHandleDataManager.updateToggolePosition(new HandletoggolButton(handletoggolButtons.get(i).getId(),position));
                            }
                        }

                        notifyDataSetChanged();
                        Toast.makeText(context, "can delete "+position, Toast.LENGTH_SHORT).show();
                    }



                    return true;
                }
            });


            mgs.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    handletoggolButtons = toggolButtonHandleDataManager.getAllMgsEmlToggolPosition();
                    if (isChecked) {

                        insertToggolButtonPosition(position);
                        Toast.makeText(context, String.valueOf("message"), Toast.LENGTH_SHORT).show();


                    } else {

                        toggolButtonHandleDataManager.deleteToggolButtonPositon(position);
                        Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();


                    }

                }


            });
            *//*ArrayList<HandletoggolButton> handletoggolButtons = new ArrayList<>();


                    if (!isExecuted) {
                        handletoggolButtons = toggolButtonHandleDataManager.getAllMgsEmlToggolPosition();

                        for (int i = 0; i < handletoggolButtons.size(); i++) {

                            mgs.toggleButton.setTag(itemList.get(handletoggolButtons.get(i).getPositon()));
                            MessageClass messageClass1 = (MessageClass) mgs.toggleButton.getTag();
                            messageClass1.setSelected(true);
                            Toast.makeText(context, " ....." + handletoggolButtons.size(), Toast.LENGTH_SHORT).show();
                        }
                        isExecuted = true;
                    }*//*

            // setTegToggle(mgs);
        }

        mgs.toggleButton.setChecked(((MessageClass) itemList.get(position)).getSelected());


    }

    private void setTegToggle(RecyclerView.ViewHolder holder) {

        ArrayList<HandletoggolButton> handletoggolButtons = new ArrayList<>();
        EmailViewHolder eml = null;
        MessageViewwHolder mgs=null ;
        switch (holder.getItemViewType()) {
            case MGS:
                mgs = (MessageViewwHolder) holder;


                break;
            case EML:
                eml = (EmailViewHolder) holder;


                break;

        }



        if (!isExecuted) {
            handletoggolButtons = toggolButtonHandleDataManager.getAllMgsEmlToggolPosition();

            for (int i = 0; i < handletoggolButtons.size(); i++) {
                eml.toggleButtonEml.setTag(itemList.get(handletoggolButtons.get(i).getPositon()));
                EmailClass emailClass1 = (EmailClass) eml.toggleButtonEml.getTag();
                emailClass1.setSelected(true);
                mgs.toggleButton.setTag(itemList.get(handletoggolButtons.get(i).getPositon()));
                MessageClass messageClass1 = (MessageClass) mgs.toggleButton.getTag();
                messageClass1.setSelected(true);
                Toast.makeText(context, " ....." + handletoggolButtons.size(), Toast.LENGTH_SHORT).show();
            }
            isExecuted = true;
        }





    }*/

/*

private class RecyclerViewSimpleTextViewHolder extends RecyclerView.ViewHolder {
    private TextView textType;
    public RecyclerViewSimpleTextViewHolder(View v) {

        super(v);
        textType=v.findViewById(R.id.textTypeTV);

    }

    public TextView getTextType() {
        return textType;
    }

    public void setTextType(TextView textType) {
        this.textType = textType;
    }
}

    private void insertToggolButtonPosition(int position) {

        ArrayList<Integer> kdf = new ArrayList<>();
        for (int i = 0; i < handletoggolButtons.size(); i++) {
            kdf.add(handletoggolButtons.get(i).getPositon());


        }
        if (!kdf.toString().contains(String.valueOf(position))) {

            toggolButtonHandleDataManager.addToggolButtonPositon(new HandletoggolButton(position));
            Toast.makeText(context, "inserted"+position, Toast.LENGTH_SHORT).show();
        }

    }*/
