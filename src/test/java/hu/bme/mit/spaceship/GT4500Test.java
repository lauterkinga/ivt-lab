package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPrimary;
  private TorpedoStore mockSecondary;

  @BeforeEach
  public void init(){
    mockPrimary=mock(TorpedoStore.class);
    mockSecondary=mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimary, mockSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Verify
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Verify
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).fire(1);
  }


  
  @Test 
  public void fireTorpedo_Single_Twice_Success(){
     // Arrange
     when(mockPrimary.fire(1)).thenReturn(true);
     when(mockSecondary.fire(1)).thenReturn(true);
 
     // Act
     ship.fireTorpedo(FiringMode.SINGLE);
     ship.fireTorpedo(FiringMode.SINGLE);
 
     // Verify
     verify(mockPrimary, times(1)).fire(1);
     verify(mockSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_PrimaryIsEmpty_Success(){
     // Arrange
     when(mockPrimary.isEmpty()).thenReturn(true);
     when(mockSecondary.isEmpty()).thenReturn(false);
     when(mockSecondary.fire(1)).thenReturn(true);
 
     // Act
     ship.fireTorpedo(FiringMode.SINGLE);
 
     // Verify
     verify(mockPrimary, times(0)).fire(1);
     verify(mockSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Failed(){
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Verify
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice_PrimaryIsEmpty_Success(){
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    // Verify
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(2)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failed(){
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Verify
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_PrimaryIsEmpty_Success(){
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);

    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Verify
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice_SecondaryIsEmpty(){
     // Arrange
     when(mockPrimary.isEmpty()).thenReturn(false);
     when(mockSecondary.isEmpty()).thenReturn(true);
 
     // Act
     ship.fireTorpedo(FiringMode.SINGLE);
     ship.fireTorpedo(FiringMode.SINGLE);
 
     // Verify
     verify(mockPrimary, times(2)).fire(1);
     verify(mockSecondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Three_SecondaryIsEmpty(){
     // Arrange
     ship.fireTorpedo(FiringMode.SINGLE);
     when(mockPrimary.isEmpty()).thenReturn(true);
     when(mockSecondary.isEmpty()).thenReturn(true);
 
     // Act
     ship.fireTorpedo(FiringMode.SINGLE);
 
     // Verify
     verify(mockPrimary, times(1)).fire(1);
     verify(mockSecondary, times(0)).fire(1);
  }



}
