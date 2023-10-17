/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.rest.converter;

import java.util.ArrayList;
import java.util.List;

import org.dspace.app.ldn.NotifyServiceEntity;
import org.dspace.app.ldn.NotifyServiceInboundPattern;
import org.dspace.app.ldn.NotifyServiceOutboundPattern;
import org.dspace.app.rest.model.NotifyServiceInboundPatternRest;
import org.dspace.app.rest.model.NotifyServiceOutboundPatternRest;
import org.dspace.app.rest.model.NotifyServiceRest;
import org.dspace.app.rest.projection.Projection;
import org.springframework.stereotype.Component;

/**
 * This is the converter from the NotifyServiceEntity to the REST data model
 *
 * @author Mohamed Eskander (mohamed.eskander at 4science.com)
 */
@Component
public class NotifyServiceConverter implements DSpaceConverter<NotifyServiceEntity, NotifyServiceRest> {

    @Override
    public NotifyServiceRest convert(NotifyServiceEntity obj, Projection projection) {
        NotifyServiceRest notifyServiceRest = new NotifyServiceRest();

        notifyServiceRest.setProjection(projection);
        notifyServiceRest.setId(obj.getID());
        notifyServiceRest.setName(obj.getName());
        notifyServiceRest.setDescription(obj.getDescription());
        notifyServiceRest.setUrl(obj.getUrl());
        notifyServiceRest.setLdnUrl(obj.getLdnUrl());
        notifyServiceRest.setEnabled(obj.isEnabled());
        notifyServiceRest.setScore(obj.getScore());

        if (obj.getInboundPatterns() != null) {
            notifyServiceRest.setNotifyServiceInboundPatterns(
                convertInboundPatternToRest(obj.getInboundPatterns()));
        }

        if (obj.getOutboundPatterns() != null) {
            notifyServiceRest.setNotifyServiceOutboundPatterns(
                convertOutboundPatternToRest(obj.getOutboundPatterns()));
        }

        return notifyServiceRest;
    }

    private List<NotifyServiceInboundPatternRest> convertInboundPatternToRest(
        List<NotifyServiceInboundPattern> inboundPatterns) {
        List<NotifyServiceInboundPatternRest> inboundPatternRests = new ArrayList<>();
        for (NotifyServiceInboundPattern inboundPattern : inboundPatterns) {
            NotifyServiceInboundPatternRest inboundPatternRest = new NotifyServiceInboundPatternRest();
            inboundPatternRest.setPattern(inboundPattern.getPattern());
            inboundPatternRest.setConstraint(inboundPattern.getConstraint());
            inboundPatternRest.setAutomatic(inboundPattern.isAutomatic());
            inboundPatternRests.add(inboundPatternRest);
        }
        return inboundPatternRests;
    }

    private List<NotifyServiceOutboundPatternRest> convertOutboundPatternToRest(
        List<NotifyServiceOutboundPattern> outboundPatterns) {
        List<NotifyServiceOutboundPatternRest> outboundPatternRests = new ArrayList<>();
        for (NotifyServiceOutboundPattern outboundPattern : outboundPatterns) {
            NotifyServiceOutboundPatternRest outboundPatternRest = new NotifyServiceOutboundPatternRest();
            outboundPatternRest.setPattern(outboundPattern.getPattern());
            outboundPatternRest.setConstraint(outboundPattern.getConstraint());
            outboundPatternRests.add(outboundPatternRest);
        }
        return outboundPatternRests;
    }

    @Override
    public Class<NotifyServiceEntity> getModelClass() {
        return NotifyServiceEntity.class;
    }
}
