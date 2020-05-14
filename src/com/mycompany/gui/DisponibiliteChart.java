
/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.gui;

import com.mycompany.Entities.Depot;
import com.mycompany.Entities.Location;
 import com.mycompany.Entities.DepotAdresse;


import com.mycompany.Service.DepotService;
import com.mycompany.Service.LocationService;

import com.mycompany.Utils.Statics;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdk.nashorn.internal.objects.NativeString;





/**
 * Budget demo pie chart.
 */
public class DisponibiliteChart extends BaseForm{
    
    public DisponibiliteChart(Resources res) {
        
        super("List des Depots", BoxLayout.y());
       
        removeAll();
        header(res);
          show();
        
        getStyle().setBgColor(0x13a19c);
                    getStyle().setBgTransparency(0xff);
     afficherDisponibilite(res);
    }
    
      
    public void afficherDisponibilite(Resources res){
          ButtonGroup barGroup = new ButtonGroup();
        RadioButton dispo = RadioButton.createToggle("Disponibilite", barGroup);
        dispo.setUIID("SelectBar");
        RadioButton AdresseChart = RadioButton.createToggle("Par Adresse", barGroup);
        AdresseChart.setUIID("SelectBar");
       
   
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
       
         
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, dispo, AdresseChart),
                FlowLayout.encloseBottom(arrow)
        ));
        
       
        dispo.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(dispo, arrow);
        });
        bindButtonSelection(dispo, arrow);
        bindButtonSelection(AdresseChart, arrow);
        
         AdresseChart.addActionListener( l ->{
     
        removeAll();
        header(res);
          show();
        
        getStyle().setBgColor(0x13a19c);
                    getStyle().setBgTransparency(0xff);
            afficherParAdresse(res);
          });
        
         dispo.addActionListener( l ->{
         removeAll();
            header(res);
            show();
              getStyle().setBgColor(0x13a19c);
                    getStyle().setBgTransparency(0xff);
            afficherDisponibilite(res);
          });
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
     createPieChartForm();
        
         
        
    }
  
    
      public void afficherParAdresse(Resources res){
          ButtonGroup barGroup = new ButtonGroup();
        RadioButton dispo = RadioButton.createToggle("Disponibilite", barGroup);
        dispo.setUIID("SelectBar");
        RadioButton AdresseChart = RadioButton.createToggle("Par Adresse", barGroup);
        AdresseChart.setUIID("SelectBar");
       
   
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
       
         
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, dispo, AdresseChart),
                FlowLayout.encloseBottom(arrow)
        ));
        
       
        AdresseChart.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(AdresseChart, arrow);
        });
        bindButtonSelection(dispo, arrow);
        bindButtonSelection(AdresseChart, arrow);
        
         AdresseChart.addActionListener( l ->{
         removeAll();
            header(res);
            show();
              getStyle().setBgColor(0x13a19c);
                    getStyle().setBgTransparency(0xff);
            afficherParAdresse(res);
          });
        
         dispo.addActionListener( l ->{
         removeAll();
            header(res);
            show();
              getStyle().setBgColor(0x13a19c);
                    getStyle().setBgTransparency(0xff);
            afficherDisponibilite(res);
          });
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
     createPieChartAdresseForm();
        
         
        
    }
  
    
    
      /**
     * Creates a renderer for the specified colors.
     */
    
   Font smallFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_SMALL, Font.STYLE_PLAIN);
   Font medFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_MEDIUM, Font.STYLE_PLAIN);
   Font largeFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_LARGE, Font.STYLE_PLAIN);
    
    
     private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(smallFont.getHeight()/2);
    renderer.setLegendTextSize(smallFont.getHeight()/2);
    renderer.setMargins(new int[] { medFont.getHeight(), medFont.getHeight(), medFont.getHeight(), medFont.getHeight() });
    
    for (int color : colors) {
      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
      r.setColor(color);
      renderer.addSeriesRenderer(r);
    }
    return renderer;
  }
     
      private DefaultRenderer buildCategoryRenderer(int c) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(smallFont.getHeight()/2);
    renderer.setLegendTextSize(smallFont.getHeight()/2);
    renderer.setMargins(new int[] { medFont.getHeight(), medFont.getHeight(), medFont.getHeight(), medFont.getHeight() });
       int[] colors = new int[] { ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN ,ColorUtil.BLACK ,ColorUtil.GRAY ,ColorUtil.LTGRAY ,ColorUtil.WHITE};
   
       while( c>0){
      int cc = c ;   
    if (c> colors.length){
           cc= colors.length;
           
       }
       for (int i = 0; i < cc; i++) {
     
     SimpleSeriesRenderer r = new SimpleSeriesRenderer();
      r.setColor(colors[i]);
      renderer.addSeriesRenderer(r);
       
     }
        c = c - colors.length;
       }
    return renderer;
  }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        
     
            series.add("Disponible ", values[0]);
               series.add("Indisponible ", values[1]);
        
        return series;
    }
    
      protected CategorySeries buildCategoryDatasetAdresse(String title,  ArrayList<DepotAdresse> depotsAdresse) {
        CategorySeries series = new CategorySeries(title);
        
      for (int i = 0; i < depotsAdresse.size(); i++) {
        
            series.add(depotsAdresse.get(i).getAdresse()+"  :  "+depotsAdresse.get(i).getNum()+"   . ", depotsAdresse.get(i).getNum());
             
      }
        return series;
    }

    public void createPieChartForm() {
      
      int dispo =0;
      int indispo = 0;
      
          ArrayList<Depot> depots;         
         depots = DepotService.getInstance().getAllDepots();
         
          for (int i = 0; i < depots.size(); i++) {
            if (depots.get(i).getEtat().equals("dispo"))
              {
              dispo++;
              }else{
                indispo++;
            }
          }
      
    double[] values = new double[] { dispo,indispo };
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN};
       final DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
      renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
   

     
 final CategorySeries seriesSet = buildCategoryDataset("Depot disponibilite", values);
    final PieChart chart = new PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart){

        private boolean inDrag = false;
        
        @Override
        public void pointerPressed(int x, int y) {
            inDrag = false;
            super.pointerPressed(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pointerDragged(int x, int y) {
            inDrag = true;
            super.pointerDragged(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        
        
        @Override
        protected void seriesReleased(SeriesSelection sel) {
            
            if ( inDrag ){
                // Don't do this if it was a drag operation
                return;
            }
            
            for ( SimpleSeriesRenderer r : renderer.getSeriesRenderers()){
                r.setHighlighted(false);
            }
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
            r.setHighlighted(true);
            
            Shape seg = chart.getSegmentShape(sel.getPointIndex());
            Rectangle bounds = seg.getBounds();
            bounds = new Rectangle(
                    bounds.getX()-40,
                    bounds.getY()-40,
                    bounds.getWidth()+80,
                    bounds.getHeight()+80
            );
            
            this.zoomToShapeInChartCoords(bounds, 500);
            
            
            
        }
       
        
        
    };
    comp.setZoomEnabled(true);
    comp.setPanEnabled(true);
    
   // return wrap("Budget", comp);
    
        // Create a form and show it.
        Form f = new Form();
       
         f.getStyle().setBgColor(0x13a19c);
        f.getStyle().setBgTransparency(0xff);
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, comp);
        
         //Container cnt = BorderLayout.center(comp);
        
       add(f);
       show();
    }
    
    
      public XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
    renderer.setAxisTitleTextSize(smallFont.getHeight()/2);
    renderer.setChartTitleTextFont(smallFont);
    renderer.setLabelsTextSize(smallFont.getHeight()/2);
    renderer.setLegendTextSize(smallFont.getHeight()/2);
    int length = colors.length;
    for (int i = 0; i < length; i++) {
      XYSeriesRenderer r = new XYSeriesRenderer();
      r.setColor(colors[i]);
      renderer.addSeriesRenderer(r);
    }
    return renderer;
  }
    
   public void createPieChartAdresseForm() {
      
    
      
          ArrayList<DepotAdresse> depotsAdresse;         
         depotsAdresse = DepotService.getInstance().getAllAdresse();
        
      
  
        // Set up the renderer
     
       final DefaultRenderer renderer = buildCategoryRenderer(depotsAdresse.size());
    renderer.setZoomButtonsVisible(true);
      renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
   

     
 final CategorySeries seriesSet = buildCategoryDatasetAdresse("Depot Par adresse", depotsAdresse);
    final PieChart chart = new PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart){

        private boolean inDrag = false;
        
        @Override
        public void pointerPressed(int x, int y) {
            inDrag = false;
            super.pointerPressed(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pointerDragged(int x, int y) {
            inDrag = true;
            super.pointerDragged(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        
        
        @Override
        protected void seriesReleased(SeriesSelection sel) {
            
            if ( inDrag ){
                // Don't do this if it was a drag operation
                return;
            }
            
            for ( SimpleSeriesRenderer r : renderer.getSeriesRenderers()){
                r.setHighlighted(false);
            }
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
            r.setHighlighted(true);
            
            Shape seg = chart.getSegmentShape(sel.getPointIndex());
            Rectangle bounds = seg.getBounds();
            bounds = new Rectangle(
                    bounds.getX()-40,
                    bounds.getY()-40,
                    bounds.getWidth()+80,
                    bounds.getHeight()+80
            );
            
            this.zoomToShapeInChartCoords(bounds, 500);
            
            
            
        }
       
        
        
    };
    comp.setZoomEnabled(true);
    comp.setPanEnabled(true);
    
   // return wrap("Budget", comp);
    
        // Create a form and show it.
        Form f = new Form();
       
         f.getStyle().setBgColor(0x13a19c);
        f.getStyle().setBgTransparency(0xff);
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, comp);
        
         //Container cnt = BorderLayout.center(comp);
        
       add(f);
       show();
    }
    
   
    
   
  
    public void header(Resources res){
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List des Depots");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("chart2.png"), spacer1);
        addTab(swipe, res.getImage("chart1.png"), spacer2);
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
      
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
       
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                           
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }


}
