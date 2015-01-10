package com.jimulabs.googlemusicmock;

import android.graphics.Point;
import android.view.View;

import com.jimulabs.mirrorsandbox.MirrorAnimator;
import com.jimulabs.mirrorsandbox.MirrorAnimatorSandbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by lintonye on 2014-12-20.
 */
public class ChartSandbox extends MirrorAnimatorSandbox {

    public ChartSandbox(View root) {
        super(root);
    }

    @Override
    public void enterSandbox() {
        fillChartWithMockData();
        sequence($(R.id.chart).animator("spanX", 0f, 1f).duration(1000),
                $(R.id.chart).animator("spanY", 0f, 1f).duration(1000),
                showMarkers()
        ).start();
    }

    private void fillChartWithMockData() {
        ChartView chart = (ChartView) $(R.id.chart).getView();
        Point[] points = createFixedSamplePoints();
        int[] highlightIndices = {1, 5, 7, 11, 15, 18};
        chart.setData(points, highlightIndices);
    }

    private void animateChart() {
        ChartView chart = (ChartView) $(R.id.chart).getView();
        List<ChartView.HighlightDot> dots = chart.getHighlightDots();
        showChart(dots).start();
    }

    private MirrorAnimator showMarkers() {
        ChartView chart = (ChartView) $(R.id.chart).getView();
        return showHighlights(chart.getHighlightDots());
    }

    public MirrorAnimator showHighlights(List<ChartView.HighlightDot> dots) {
        Collections.sort(dots, new Comparator<ChartView.HighlightDot>() {
            @Override
            public int compare(ChartView.HighlightDot lhs, ChartView.HighlightDot rhs) {
                return lhs.x - rhs.x;
            }
        });

        List<MirrorAnimator> animators = new ArrayList<>(dots.size());
        for (int i = 0; i < dots.size(); i++) {
            MirrorAnimator showDot = wrapToAnimate(dots.get(i)).animator("radius", 0, 15f).duration(200);
            animators.add(showDot.startDelay(150 * i));
        }

        return together(animators);
    }

    private int[] createHighlightIndices(int highlightCount, int totalCount) {
        int[] result = new int[highlightCount];
        Random r = new Random();
        for (int i = 0; i < highlightCount; i++) {
            result[i] = r.nextInt(totalCount);
        }
        return result;
    }

    public MirrorAnimator hideChart(List<ChartView.HighlightDot> dots) {
        return sq(hideDots(dots), shrinkY(), shrinkX()).startDelay(1000);
    }

    public MirrorAnimator hideDots(List<ChartView.HighlightDot> dots) {
        List<MirrorAnimator> animators = new ArrayList<>(dots.size());
        for (int i = 0; i < dots.size(); i++) {
            MirrorAnimator showDot = wrapToAnimate(dots.get(i)).animator("radius", 8f, 0f).duration(200);
            animators.add(showDot);

        }
        return together(animators);

    }

    public MirrorAnimator showChart(List<ChartView.HighlightDot> dots) {
        return sq(expandX(), expandY(), showHighlights(dots));
    }

    public MirrorAnimator shrinkX() {
        return spanX(1f, 0f);
    }

    public MirrorAnimator shrinkY() {
        return spanY(1f, 0f);
    }

    public MirrorAnimator expandX() {
        return spanX(0f, 1f);
    }

    public MirrorAnimator expandY() {
        return spanY(0f, 1f);
    }

    private MirrorAnimator spanX(float startSpanX, float endSpanX) {
        return $(R.id.chart).animator("spanX", startSpanX, endSpanX).duration(1100);
    }

    private MirrorAnimator spanY(float startSpanY, float endSpanY) {
        return $(R.id.chart).animator("spanY", startSpanY, endSpanY).duration(1100);
    }

    private List<Point> createRandomPoints(int count, int maxX, int maxY) {
        List<Point> points = new ArrayList<>(count);
        Random random = new Random();
        int offsetY = maxY;
        int marginX = 5;
        for (int i = 0; i < count; i++) {
            int x = marginX + i * ((maxX + 2 * marginX) / (count - 1));
            int y = random.nextInt(maxY) + offsetY;
            points.add(new Point(x, y));
        }
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                return lhs.x - rhs.x;
            }
        });
        return points;
    }

    private Point[] createFixedSamplePoints() {
        Point[] points = {new Point(5, 415),
                new Point(42, 352),
                new Point(79, 418),
                new Point(116, 389),
                new Point(153, 381),
                new Point(190, 656),
                new Point(227, 439),
                new Point(264, 311),
                new Point(301, 110),
                new Point(338, 312),
                new Point(375, 418),
                new Point(412, 317),
                new Point(449, 334),
                new Point(486, 278),
                new Point(523, 437),
                new Point(560, 405),
                new Point(597, 367),
                new Point(634, 272),
                new Point(671, 331),
                new Point(708, 294),
        };
        return points;
    }


    private void fillChartWithRandomData() {
        int totalCount = 25;
        List<Point> points = createRandomPoints(totalCount, 800, 400);
        ChartView chartView = (ChartView) $(R.id.chart).getView();
        chartView.setData(points.toArray(new Point[0]),
                createHighlightIndices(5, totalCount));
    }

}
